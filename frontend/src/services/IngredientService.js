import http from "./index";

class IngredientService {
  getAll(params) {
    return http.get("/ingredients", params);
  }
  getAllWithoutPaging() {
    return http.get("/ingredients/get-all-without-paging");
  }
  add(ingredient, images, certificates) {
    let formData = new FormData();
    const blob = new Blob([JSON.stringify(ingredient)], {
      type: "application/json",
    });

    formData.append("ingredient", blob);
    certificates.forEach((certificate) =>
      formData.append("certificates", certificate)
    );
    images.forEach((image) => formData.append("images", image));

    return http.post(`/ingredients`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
  }
  detail(id) {
    return http.get(`/ingredients/${id}`);
  }
}

export default IngredientService;
