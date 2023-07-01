import http from "./index";

class PackageService {
  getAll(params) {
    return http.get("/packages", params);
  }
  detail(sku) {
    return http.get(`/packages/${sku}`);
  }
  add(packageDto, images) {
    let formData = new FormData();
    const blob = new Blob([JSON.stringify(packageDto)], {
      type: "application/json",
    });

    formData.append("packageDto", blob);
    images.forEach((image) => formData.append("images", image));

    return http.post("/packages", formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
  }
  getAllStamps(sku, params) {
    return http.get(`/packages/stamps/${sku}`, params);
  }
  trace(sku) {
    return http.get(`/trace/${sku}`);
  }
}

export default PackageService;
