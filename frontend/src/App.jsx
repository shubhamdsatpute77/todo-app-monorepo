import {useEffect, useState} from "react";
import {
    Container,
    Form,
    Button,
    ListGroup,
    Row,
    Col
} from "react-bootstrap";

import 'bootstrap/dist/css/bootstrap.min.css';
import backendAPI from "./backend.js";

function App() {
    const [todoList, setTodoList] = useState({
        title : "",
        description : ""
    });

    const [todos, setTodos] = useState([]);

    const fetchTodos = () => {
        backendAPI.get("")
            .then(res => setTodos(res.data))
            .catch(err => console.error(err));
    }

    useEffect(() => {
        fetchTodos();
    }, []);

    const submitForm = (e) => {
        e.preventDefault();
        backendAPI.post("", todoList)
            .then(res => {
                setTodos([...todos, res.data]);
                setTodoList({
                    title : "",
                    description: ""
                });
            })
            .catch(err => console.error(err));
    };

    return (
        <Container className="mt-5">
            <Row className="justify-content-center">
                <Col md={10}>
                    <h1 className="text-center mb-4">Tasking</h1>

                    <Form onSubmit={submitForm} className="d-flex gap-2 mb-4">
                        <Form.Control
                            type="text"
                            placeholder="Enter Title"
                            value={todoList.title}
                            onChange={(e) => setTodoList({
                                ...todoList,
                                title : e.target.value
                            })}
                        />
                        <Form.Control
                            type="text"
                            placeholder="Enter Description"
                            value={todoList.description}
                            onChange={(e) => setTodoList({
                                ...todoList,
                                description : e.target.value
                            })}
                        />
                        <Button variant="primary" type="submit">
                            Add
                        </Button>
                    </Form>

                    <h5>Task List</h5>
                    <ListGroup>
                        {todos.map((todo) => (
                            <ListGroup.Item key={todo.id} className="mb-1">
                                <TodoTile todo={todo} fetchTodos={() => fetchTodos()}/>
                            </ListGroup.Item>
                        ))}
                    </ListGroup>
                </Col>
            </Row>
        </Container>
    );
}

export default App;

const TodoTile = ({todo, fetchTodos}) => {
    const onDelete = async (id) => {
        await backendAPI.delete("/" + id);
        await fetchTodos();
    }

    const onComplete = async (id, status) => {
        await backendAPI.get("/" + id + "/complete/" + status);
        await fetchTodos();
    }
    return (
        <div>
            <div className="d-flex justify-content-between">
                <div>
                    {todo.title}
                </div>
                <div className="d-flex gap-2">
                    <Button variant="outline-dark">Edit</Button>
                    <Button variant="outline-dark" onClick={() => onDelete(todo.id)}>Delete</Button>
                    <Button variant="outline-dark" onClick={() => onComplete(todo.id, !todo.completed)}>
                        {todo.completed ? "Open" : "Complete"}
                    </Button>
                </div>
            </div>
            <div>{todo.description}</div>
        </div>
    );
}
