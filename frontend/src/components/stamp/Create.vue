<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div class="p-4 bg-white">
    <div
      class="block sm:flex items-center justify-between lg:mt-1.5 dark:bg-gray-800 dark:border-gray-700"
    >
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
                  Tem truy xuất
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
                    >Tạo tem truy xuất</a
                  >
                </div>
              </li>
            </ol>
          </nav>
          <h1
            class="text-xl font-semibold text-gray-900 sm:text-2xl dark:text-white"
          >
            Tạo tem truy xuất
          </h1>
        </div>
      </div>
    </div>

    <div class="grid gap-4 grid-cols-2">
      <form class="space-y-6" prevent.submit>
        <div>
          <label
            for="name"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Mã lô hàng</label
          >
          <input
            type="text"
            name="number"
            v-model="packageDto.sku"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"
            placeholder=""
            required
          />
        </div>
        <div>
          <label
            for="name"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Tên sản phẩm đóng gói</label
          >
          <input
            type="text"
            name="number"
            v-model="packageDto.productName"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"
            placeholder=""
            required
          />
        </div>
        <div>
          <label
            for="name"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Sản phẩm nguyên liệu</label
          >
          <multiselect
            v-model="value"
            :options="products"
            label="name"
            track-by="name"
          ></multiselect>
        </div>

        <div class="grid grid-cols-2 gap-4">
          <div class="w-full">
            <label
              for="name"
              class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
              >Ngày đóng gói</label
            >
            <div class="relative w-full">
              <div
                class="absolute inset-y-0 left-0 flex items-center pl-3 pointer-events-none"
              >
                <svg
                  aria-hidden="true"
                  class="w-5 h-5 text-gray-500 dark:text-gray-400"
                  fill="currentColor"
                  viewBox="0 0 20 20"
                  xmlns="http://www.w3.org/2000/svg"
                >
                  <path
                    fill-rule="evenodd"
                    d="M6 2a1 1 0 00-1 1v1H4a2 2 0 00-2 2v10a2 2 0 002 2h12a2 2 0 002-2V6a2 2 0 00-2-2h-1V3a1 1 0 10-2 0v1H7V3a1 1 0 00-1-1zm0 5a1 1 0 000 2h8a1 1 0 100-2H6z"
                    clip-rule="evenodd"
                  ></path>
                </svg>
              </div>
              <input
                type="text"
                id="packageDate"
                @onChange="console.log('Hello')"
                class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full pl-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                placeholder="Select date"
              />
            </div>
          </div>
          <div class="w-full">
            <label
              for="name"
              class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
              >Ngày hết hạn</label
            >
            <div class="relative w-full">
              <div
                class="absolute inset-y-0 left-0 flex items-center pl-3 pointer-events-none"
              >
                <svg
                  aria-hidden="true"
                  class="w-5 h-5 text-gray-500 dark:text-gray-400"
                  fill="currentColor"
                  viewBox="0 0 20 20"
                  xmlns="http://www.w3.org/2000/svg"
                >
                  <path
                    fill-rule="evenodd"
                    d="M6 2a1 1 0 00-1 1v1H4a2 2 0 00-2 2v10a2 2 0 002 2h12a2 2 0 002-2V6a2 2 0 00-2-2h-1V3a1 1 0 10-2 0v1H7V3a1 1 0 00-1-1zm0 5a1 1 0 000 2h8a1 1 0 100-2H6z"
                    clip-rule="evenodd"
                  ></path>
                </svg>
              </div>
              <input
                type="text"
                id="expiryDate"
                class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full pl-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                placeholder="Select date"
              />
            </div>
          </div>
        </div>

        <div class="grid grid-cols-2 gap-4">
          <div>
            <label
              for="name"
              class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
              >Số lượng</label
            >
            <input
              type="text"
              name="number"
              v-model="packageDto.quantity"
              class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"
              placeholder=""
              required
            />
          </div>
          <div>
            <label
              for="name"
              class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
              >Khối lượng</label
            >
            <input
              type="text"
              name="name"
              v-model="packageDto.weight"
              class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"
              placeholder=""
              required
            />
          </div>
        </div>
        <div class="grid grid-cols-3 gap-4">
          <div>
            <label
              for="name"
              class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
              >Đơn vị cơ bản</label
            >
            <input
              type="text"
              name="name"
              v-model="packageDto.basicUnit"
              class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"
              placeholder=""
              required
            />
          </div>
          <div>
            <label
              for="name"
              class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
              >Đơn vị quy đổi</label
            >
            <input
              type="text"
              name="name"
              v-model="packageDto.conversionUnit"
              class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"
              placeholder=""
              required
            />
          </div>
          <div>
            <label
              for="name"
              class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
              >Giá trị quy đổi</label
            >
            <input
              type="number"
              name="name"
              v-model="packageDto.conversionValue"
              class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"
              placeholder=""
              required
            />
          </div>
        </div>
        <div>
          <label
            for="name"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Địa chỉ sản xuất</label
          >
          <input
            type="text"
            name="name"
            v-model="packageDto.address"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"
            placeholder=""
            required
          />
        </div>
        <div>
          <label
            for="name"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Số lượt quét tem tối đa</label
          >
          <input
            type="number"
            name="name"
            v-model="packageDto.maxScanTimes"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"
            placeholder=""
            required
          />
        </div>

        <button
          type="button"
          @click="createPackage"
          class="w-full text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
        >
          Hoàn thành
        </button>
      </form>
      <div class="pb-4">
        <label
          for="name"
          class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
          >Hình ảnh</label
        >
        <FileUpload name="images" />
      </div>
    </div>
  </div>
</template>

<script>
import { Modal } from "flowbite";
import Datepicker from "flowbite-datepicker/Datepicker";
import Multiselect from "vue-multiselect";
import PackageService from "../../services/PackageService";
import ProductService from "../../services/ProductService";
import FileUpload from "../file/FileUpload.vue";
import { useToast } from "vue-toastification";
const packageService = new PackageService();
const productService = new ProductService();
const toast = useToast();

export default {
  components: {
    FileUpload,
    Multiselect,
  },
  data() {
    return {
      packageDto: {
        sku: "",
        productName: "",
        packageDate: "",
        expiryDate: "",
        quantity: 0,
        weight: 0,
        basicUnit: "",
        conversionUnit: "",
        conversionValue: "",
        address: "",
        maxScanTimes: 0,
        productId: 0,
      },
      packageDateEl: null,
      expiryDateEl: null,
      value: null,
      products: [],
    };
  },
  async created() {
    const res = await productService.getAllWithoutPaging();

    if (res.status === 200) {
      this.products = res.data;
    }
  },
  mounted() {
    this.packageDateEl = document.getElementById("packageDate");
    this.expiryDateEl = document.getElementById("expiryDate");
    new Datepicker(this.packageDateEl, {
      autohide: true,
      format: "dd/mm/yyyy",
      orientation: "bottom left",
    });
    new Datepicker(this.expiryDateEl, {
      autohide: true,
      format: "dd/mm/yyyy",
      orientation: "bottom left",
    });
  },
  methods: {
    showCreateStampModal() {
      const ref = document.getElementById("create-stamp-modal");
      const modal = new Modal(ref);
      modal.show();
    },
    async createPackage() {
      this.packageDto.packageDate = this.convertDate(this.packageDateEl.value);
      this.packageDto.expiryDate = this.convertDate(this.expiryDateEl.value);
      this.packageDto.productId = this.value.id;

      const images = JSON.parse(localStorage.getItem("images"));

      const imageBlobs = await Promise.all(
        images.map(async (image) => {
          // get blob from blob url
          let blob = await fetch(image.url).then((r) => r.blob());

          // convert blob to file object
          const file = new File([blob], image.name, { type: image.type });
          return file;
        })
      );

      const res = await packageService.add(this.packageDto, imageBlobs);

      if (res.status === 200) {
        toast.success("Tạo lô hàng thành công");
        localStorage.removeItem("images");
      }
    },
    convertDate(date) {
      const dateArr = date.split("/");
      return `${dateArr[2]}-${dateArr[1]}-${dateArr[0]}`;
    },
  },
};
</script>

<style src="vue-multiselect/dist/vue-multiselect.css"></style>
