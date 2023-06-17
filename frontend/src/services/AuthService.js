import http from "./index";

class AuthService {
  login(data) {
    return http.post("/auth/login", data);
  }
  
}

export default AuthService;
