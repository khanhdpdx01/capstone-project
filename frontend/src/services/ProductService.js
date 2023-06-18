import http from "./index";

class ProductService {
  getAll(params) {
    return http.get("/products", params);
  }
  getAllWithoutPaging() {
    return http.get("/products/get-all-without-paging");
  }
  add(ingredient, images, certificates) {
    let formData = new FormData();
    const blob = new Blob([JSON.stringify(ingredient)], {
      type: "application/json",
    });

    formData.append("product", blob);
    certificates.forEach((certificate) =>
      formData.append("certificates", certificate)
    );
    images.forEach((image) => formData.append("images", image));

    return http.post(`/products`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
  }
  detail(id) {
    return http.get(`/products/${id}`);
  }
}

export default ProductService;
