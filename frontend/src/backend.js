import axios from "axios";

const backendAPI = axios.create({
    baseURL: "http://localhost:8080/api/v1/todos"
});

export default backendAPI;
