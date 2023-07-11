<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div>
    <div
      class="p-4 bg-white block sm:flex items-center justify-between border-gray-200 lg:mt-1.5 dark:bg-gray-800 dark:border-gray-700"
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
                  Lô hàng
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
                    >Chi tiết lô hàng</a
                  >
                </div>
              </li>
            </ol>
          </nav>
          <div class="flex justify-between">
            <h1
              class="text-xl font-semibold text-gray-900 sm:text-2xl dark:text-white"
            >
              Chi tiết lô hàng
            </h1>
          </div>
        </div>
      </div>
    </div>
    <div class="w-full h-full px-20 bg-white">
      <div class="pb-5 border-b">
        <div class="w-1/2">
          <h2 class="text-xl font-semibold text-gray-900">Thông tin chung</h2>
          <div class="grid grid-cols-2 gap-4 mt-4">
            <div>
              <h3 class="font-semibold text-gray-900">Mã lô hàng</h3>
              <span>{{ packageDto.sku }}</span>
            </div>
            <div>
              <h3 class="font-semibold text-gray-900">Tên sản phẩm</h3>
              <span>{{ packageDto.productName }}</span>
            </div>
            <div>
              <h3 class="font-semibold text-gray-900">Ngày tạo</h3>
              <span>{{ formatVNDate(packageDto.packageDate) }}</span>
            </div>
            <div>
              <h3 class="font-semibold text-gray-900">Hạn sử dụng</h3>
              <span>{{ formatVNDate(packageDto.expiryDate) }}</span>
            </div>
            <div>
              <h3 class="font-semibold text-gray-900">Số lượng</h3>
              <span>{{ packageDto.quantity }} {{ packageDto.basicUnit }}</span>
            </div>
            <div>
              <h3 class="font-semibold text-gray-900">Địa chỉ</h3>
              <span>{{ packageDto.address }}</span>
            </div>
          </div>
        </div>
      </div>

      <div class="flex flex-col">
        <div class="overflow-x-auto">
          <div class="inline-block min-w-full align-middle">
            <div class="overflow-hidden shadow">
              <table
                class="min-w-full divide-y divide-gray-200 table-fixed dark:divide-gray-600"
              >
                <thead class="bg-gray-100 dark:bg-gray-700">
                  <tr>
                    <th scope="col" class="p-4">
                      <div class="flex items-center">
                        <input
                          id="checkbox-all"
                          aria-describedby="checkbox-1"
                          type="checkbox"
                          class="w-4 h-4 border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-primary-300 dark:focus:ring-primary-600 dark:ring-offset-gray-800 dark:bg-gray-700 dark:border-gray-600"
                        />
                        <label for="checkbox-all" class="sr-only"
                          >checkbox</label
                        >
                      </div>
                    </th>
                    <th
                      scope="col"
                      class="p-4 text-xs font-medium text-left text-gray-500 uppercase dark:text-gray-400"
                    >
                      Tên sổ ghi chép
                    </th>
                    <th
                      scope="col"
                      class="p-4 text-xs font-medium text-left text-gray-500 uppercase dark:text-gray-400"
                    >
                      Sản phẩm
                    </th>
                    <th
                      scope="col"
                      class="p-4 text-xs font-medium text-left text-gray-500 uppercase dark:text-gray-400"
                    >
                      Thể loại
                    </th>
                    <!-- <th
                      scope="col"
                      class="p-4 text-xs font-medium text-left text-gray-500 uppercase dark:text-gray-400"
                    >
                      Trạng thái
                    </th> -->
                    <th
                      scope="col"
                      class="p-4 text-xs font-medium text-left text-gray-500 uppercase dark:text-gray-400"
                    >
                      Hành động
                    </th>
                  </tr>
                </thead>
                <tbody
                  class="bg-white divide-y divide-gray-200 dark:bg-gray-800 dark:divide-gray-700"
                >
                  <tr
                    class="hover:bg-gray-100 dark:hover:bg-gray-700"
                    v-for="stamp in stamps"
                    :key="stamp.id"
                  >
                    <td class="w-4 p-4">
                      <div class="flex items-center">
                        <input
                          id="checkbox-{{ stamp.id }}"
                          aria-describedby="checkbox-1"
                          type="checkbox"
                          class="w-4 h-4 border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-primary-300 dark:focus:ring-primary-600 dark:ring-offset-gray-800 dark:bg-gray-700 dark:border-gray-600"
                        />
                        <label for="checkbox-{{ stamp.id }}" class="sr-only"
                          >checkbox</label
                        >
                      </div>
                    </td>
                    <td
                      class="p-4 text-base font-medium text-gray-900 whitespace-nowrap dark:text-white"
                    >
                      {{ stamp.code }}
                    </td>
                    <td
                      class="p-4 text-base font-medium text-gray-900 whitespace-nowrap dark:text-white"
                    >
                      {{ stamp.sku.sku }}
                    </td>
                    <td
                      class="max-w-sm p-4 overflow-hidden text-base font-normal text-gray-500 truncate xl:max-w-xs dark:text-gray-400"
                    >
                      Tem sản phẩm
                    </td>
                    <!-- <td
                      class="max-w-sm p-4 overflow-hidden text-base font-normal text-gray-500 truncate xl:max-w-xs dark:text-gray-400"
                    >
                      {{ stamp.status }}
                    </td> -->
                    <td class="p-4 space-x-2 whitespace-nowrap">
                      <button
                        @click="detailStamp(stamp.code)"
                        type="button"
                        id="updateProductButton"
                        class="inline-flex items-center px-3 py-2 text-sm font-medium text-center text-white rounded-lg bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                      >
                        <svg
                          class="w-4 h-4 mr-2"
                          fill="currentColor"
                          viewBox="0 0 20 20"
                          xmlns="http://www.w3.org/2000/svg"
                        >
                          <path
                            d="M17.414 2.586a2 2 0 00-2.828 0L7 10.172V13h2.828l7.586-7.586a2 2 0 000-2.828z"
                          ></path>
                          <path
                            fill-rule="evenodd"
                            d="M2 6a2 2 0 012-2h4a1 1 0 010 2H4v10h10v-4a1 1 0 112 0v4a2 2 0 01-2 2H4a2 2 0 01-2-2V6z"
                            clip-rule="evenodd"
                          ></path>
                        </svg>
                        Chi tiết
                      </button>
                      <button
                        @click="createQrCode(stamp.code)"
                        type="button"
                        id="updateProductButton"
                        class="inline-flex items-center px-3 py-2 text-sm font-medium text-center text-white rounded-lg bg-green-700 hover:bg-green-800 focus:ring-4 focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                      >
                        <svg
                          class="w-4 h-4 mr-2"
                          fill="currentColor"
                          viewBox="0 0 20 20"
                          xmlns="http://www.w3.org/2000/svg"
                        >
                          <path
                            d="M17.414 2.586a2 2 0 00-2.828 0L7 10.172V13h2.828l7.586-7.586a2 2 0 000-2.828z"
                          ></path>
                          <path
                            fill-rule="evenodd"
                            d="M2 6a2 2 0 012-2h4a1 1 0 010 2H4v10h10v-4a1 1 0 112 0v4a2 2 0 01-2 2H4a2 2 0 01-2-2V6z"
                            clip-rule="evenodd"
                          ></path>
                        </svg>
                        Tạo QR Code
                      </button>
                      <!-- <button
                        type="button"
                        id="deleteProductButton"
                        class="inline-flex items-center px-3 py-2 text-sm font-medium text-center text-white bg-red-700 rounded-lg hover:bg-red-800 focus:ring-4 focus:ring-red-300 dark:focus:ring-red-900"
                      >
                        <svg
                          class="w-4 h-4 mr-2"
                          fill="currentColor"
                          viewBox="0 0 20 20"
                          xmlns="http://www.w3.org/2000/svg"
                        >
                          <path
                            fill-rule="evenodd"
                            d="M9 2a1 1 0 00-.894.553L7.382 4H4a1 1 0 000 2v10a2 2 0 002 2h8a2 2 0 002-2V6a1 1 0 100-2h-3.382l-.724-1.447A1 1 0 0011 2H9zM7 8a1 1 0 012 0v6a1 1 0 11-2 0V8zm5-1a1 1 0 00-1 1v6a1 1 0 102 0V8a1 1 0 00-1-1z"
                            clip-rule="evenodd"
                          ></path>
                        </svg>
                        Xóa
                      </button> -->
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>

        <div
          class="sticky bottom-0 right-0 w-full p-4 bg-white border-t border-gray-200 dark:bg-gray-800 dark:border-gray-700"
        >
          <pagination
            :total-pages="page.totalPages"
            :current-page="page.currentPage"
            @pagechanged="onPageChange"
          />
        </div>
      </div>
    </div>

    <div
      id="create-qr-code"
      tabindex="-1"
      aria-hidden="true"
      class="fixed top-0 left-0 right-0 z-50 hidden w-full p-4 overflow-x-hidden overflow-y-auto md:inset-0 h-[calc(100%-1rem)] max-h-full"
    >
      <div class="relative">
        <!-- Modal content -->
        <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
          <div class="p-4">
            <VueQRCodeComponent :text="message" error-level="L" />
            <h2 class="text-center font-bold mt-4">{{code}}</h2>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Modal } from "flowbite";
import Pagination from "../pagination/Pagination.vue";
import PackageService from "../../services/PackageService";
import VueQRCodeComponent from "vue-qrcode-component";
const packageService = new PackageService();

export default {
  components: {
    Pagination,
    VueQRCodeComponent,
  },
  data() {
    return {
      page: {
        totalPages: 1,
        totalItems: 5,
        currentPage: 1,
      },
      packageDto: {},
      stamps: [],
      id: 0,
      message: "",
      code: ""
    };
  },
  async created() {
    const id = this.$route.params.id;
    this.id = id;
    await Promise.all([this.getDetailPackage(id), this.getAllStamps(id)]);
  },
  methods: {
    async getDetailPackage(id) {
      const res = await packageService.detail(id);

      if (res.status === 200) {
        this.packageDto = res.data;
      }
    },
    async onPageChange(page) {
      this.page.currentPage = page;
      this.getAllStamps(this.id);
    },
    async getAllStamps(id) {
      const res = await packageService.getAllStamps(id, {
        params: {
          page: this.page.currentPage,
          size: this.page.totalItems,
        },
      });
      console.log(res.data);

      if (res.status === 200) {
        const { data, currentPage, totalPages } = res.data;
        this.page.totalPages = totalPages;
        this.page.currentPage = currentPage;
        this.stamps = data;
      }
    },
    detailStamp(id) {
      this.$router.push({ path: `/trace/${id}` });
    },
    createQrCode(url) {
      this.code = url;
      this.message = `http://${window.location.hostname}:8000/trace/${url}`;
      const ref = document.getElementById("create-qr-code");
      this.modal = new Modal(ref);
      this.modal.show();
    },
    formatVNDate(date) {
      return new Date(date).toLocaleDateString("vi-VN");
    },
  },
};
</script>

<style></style>
