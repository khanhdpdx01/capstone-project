import http from "./index";

class StampService {
  add(stamp) {
    return http.post("/stamps", stamp);
  }
}

export default StampService;
