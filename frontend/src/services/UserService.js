import http from "./index";

class UserService {
    getUser(id) {
        return http.get(`/users/${id}`);
    }
}

export default UserService;