<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div class="bg-white dark:bg-gray-800">
    <div class="p-4 block sm:flex items-center justify-between lg:mt-1.5">
      <div class="w-full mb-1">
        <div class="mb-4">
          <nav class="flex mb-5" aria-label="Breadcrumb">
            <ol
              class="inline-flex items-center space-x-1 text-sm font-medium md:space-x-2"
            >
              <li class="inline-flex items-center">
                <a
                  href="#"
                  class="inline-flex items-center text-gray-700 hover:text-primary-600 dark:text-gray-300 dark:hover:text-white"
                >
                  <svg
                    class="w-5 h-5 mr-2.5"
                    fill="currentColor"
                    viewBox="0 0 20 20"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <path
                      d="M10.707 2.293a1 1 0 00-1.414 0l-7 7a1 1 0 001.414 1.414L4 10.414V17a1 1 0 001 1h2a1 1 0 001-1v-2a1 1 0 011-1h2a1 1 0 011 1v2a1 1 0 001 1h2a1 1 0 001-1v-6.586l.293.293a1 1 0 001.414-1.414l-7-7z"
                    ></path>
                  </svg>
                  Sản phẩm
                </a>
              </li>
              <li>
                <div class="flex items-center">
                  <svg
                    class="w-6 h-6 text-gray-400"
                    fill="currentColor"
                    viewBox="0 0 20 20"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <path
                      fill-rule="evenodd"
                      d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z"
                      clip-rule="evenodd"
                    ></path>
                  </svg>
                  <a
                    href="#"
                    class="ml-1 text-gray-700 hover:text-primary-600 md:ml-2 dark:text-gray-300 dark:hover:text-white"
                    >Thêm mới sản phẩm</a
                  >
                </div>
              </li>
            </ol>
          </nav>
          <h1
            class="text-xl font-semibold text-gray-900 sm:text-2xl dark:text-white"
          >
            Thêm mới sản phẩm
          </h1>
        </div>
      </div>
    </div>
    <div class="grid gap-4 grid-cols-2">
      <div class="px-4">
        <div class="pb-4">
          <label
            for="name"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Tên sản phẩm</label
          >
          <input
            type="text"
            name="name"
            id="name"
            v-model="product.name"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-primary-500 dark:focus:border-primary-500"
            required=""
          />
        </div>
        <div class="pb-4">
          <label
            for="name"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Mã sản phẩm</label
          >
          <input
            type="text"
            name="name"
            id="name"
            v-model="product.gtinCode"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-primary-500 dark:focus:border-primary-500"
            required=""
          />
        </div>

        <div class="sm:col-span-2">
          <label
            for="description"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Mô tả</label
          >
          <textarea
            id="description"
            v-model="product.description"
            rows="8"
            class="block p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-primary-500 focus:border-primary-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-primary-500 dark:focus:border-primary-500"
            placeholder="Your description here"
          ></textarea>
        </div>
      </div>
      <div class="px-4">
        <div class="pb-4">
          <label
            for="name"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Hình ảnh</label
          >
          <FileUpload name="images" />
        </div>
        <div class="pb-4">
          <label
            for="name"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Chứng nhận</label
          >
          <FileUpload name="certificates" />
        </div>
        <button
          type="button"
          @click="addProduct"
          class="inline-flex items-center px-3 py-2 text-sm font-medium text-center text-white bg-red-700 rounded-lg hover:bg-red-800 focus:ring-4 focus:ring-red-300 dark:focus:ring-red-900"
        >
          Hoàn thành
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import ProductService from "../../services/ProductService";
import FileUpload from "../file/FileUpload.vue";
import { useToast } from "vue-toastification";
const toast = useToast();
const productService = new ProductService();

export default {
  components: {
    FileUpload,
  },
  data() {
    return {
      product: {
        id: 0,
        name: "",
        gtinCode: "",
        description: "",
        status: "IN_PRODUCTION",
        createdAt: "2023-05-07T10:26:04.163Z",
        updatedAt: "2023-05-08T09:19:41.616+00:00",
        rawMaterialId: 1,
        createdBy: 0,
      },
    };
  },
  async created() {
    const id = this.$route.params.id;
    console.log(id);
    if (id !== undefined) {
      const res = await productService.detail(id);

      if (res.status === 200) {
        this.product.id = res.data.id;
        this.product.name = res.data.name;
        this.product.gtinCode = res.data.gtinCode;
        this.product.description = res.data.description;
        this.product.status = res.data.status;
        this.product.createdAt = res.data.createdAt;
        this.product.rawMaterialId = res.data.rawMaterial.id;
      }
    }
  },
  methods: {
    async addProduct() {
      const images = JSON.parse(localStorage.getItem("images"));
      const certificates = JSON.parse(localStorage.getItem("certificates"));

      const imageBlobs = await Promise.all(
        images.map(async (image) => {
          // get blob from blob url
          let blob = await fetch(image.url).then((r) => r.blob());

          // convert blob to file object
          const file = new File([blob], image.name, { type: image.type });
          return file;
        })
      );

      const certificateBlobs = await Promise.all(
        certificates.map(async (image) => {
          let blob = await fetch(image.url).then((r) => r.blob());
          const file = new File([blob], image.name, { type: image.type });
          return file;
        })
      );

      if (this.id === undefined) {
        // eslint-disable-next-line no-unused-vars
        const { id: _, ...productObj } = this.product;
        const res = await productService.add(
          productObj,
          imageBlobs,
          certificateBlobs
        );

        if (res.status === 200) {
          toast.success("Tạo sản phẩm thành công!");
          this.$router.push({ path: "/dashboard/products" });
          localStorage.removeItem("images");
          localStorage.removeItem("certificates");
        }
      } else {
        const res = await productService.add(
          this.product,
          imageBlobs,
          certificateBlobs
        );

        if (res.status === 200) {
          toast.success("Cập nhật sản phẩm thành công!");
          this.$router.push({ path: "/dashboard/products" });
          localStorage.removeItem("images");
          localStorage.removeItem("certificates");
        }
      }
    },
  },
};
</script>

<style></style>
