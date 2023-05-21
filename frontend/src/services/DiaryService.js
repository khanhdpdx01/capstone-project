import http from "./index";

class DiaryService {
  getAll(params) {
    return http.get("/diaries", params);
  }

  create(diary) {
    return http.post("/api/diaries", diary);
  }
}

export default DiaryService;
