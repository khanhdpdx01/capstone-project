import http from "./index";

class DiaryService {
  getAll(params) {
    return http.get("/diaries", params);
  }

  detail(id) {
    return http.get(`/diaries/${id}`);
  }
  
  create(diary) {
    return http.post("/diaries", diary);
  }

  add(diary, images) {
    let formData = new FormData();
    const blob = new Blob([JSON.stringify(diary)], {
      type: "application/json",
    });

    formData.append("diary-detail", blob);
    images.forEach((image) => formData.append("images", image));

    return http.post(`/diary-details`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
  }
}

export default DiaryService;
