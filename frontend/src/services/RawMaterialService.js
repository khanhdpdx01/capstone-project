import http from "./index";

class RawMaterialService {
  add(rawMaterial, images, certificates) {
    let formData = new FormData();
    const blob = new Blob([JSON.stringify(rawMaterial)], {
      type: "application/json",
    });

    formData.append("raw-material", blob);
    certificates.forEach((certificate) =>
      formData.append("certificates", certificate)
    );
    images.forEach((image) => formData.append("images", image));

    return http.post(`/raw-materials`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
  }
  detail(id) {
    return http.get(`/raw-materials/${id}`);
  }
}

export default RawMaterialService;
