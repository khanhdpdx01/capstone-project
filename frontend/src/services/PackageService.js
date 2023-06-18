import http from "./index";

class PackageService {
  getAll(params) {
    return http.get("/packages", params);
  }
  add(packageDto) {
    return http.post("/packages", packageDto);
  }
}

export default PackageService;
