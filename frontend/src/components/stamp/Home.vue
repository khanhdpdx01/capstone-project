<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div>
    <div
      class="p-4 bg-white block sm:flex items-center justify-between border-b border-gray-200 lg:mt-1.5 dark:bg-gray-800 dark:border-gray-700"
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
                    >Danh sách lô hàng</a
                  >
                </div>
              </li>
            </ol>
          </nav>
          <h1
            class="text-xl font-semibold text-gray-900 sm:text-2xl dark:text-white"
          >
            Danh sách lô hàng
          </h1>
        </div>
        <div
          class="items-center justify-between block sm:flex md:divide-x md:divide-gray-100 dark:divide-gray-700"
        >
          <div class="flex items-center mb-4 sm:mb-0">
            <form class="sm:pr-3" action="#" method="GET">
              <label for="products-search" class="sr-only">Search</label>
              <div class="relative w-48 mt-1 sm:w-64 xl:w-96">
                <input
                  type="text"
                  name="email"
                  id="products-search"
                  class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-500 focus:border-primary-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-primary-500 dark:focus:border-primary-500"
                  placeholder="Tìm theo tên hoặc mã sản phẩm"
                />
              </div>
            </form>
          </div>
          <button
            @click="createPackaging"
            id="createProductButton"
            class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
            type="button"
          >
            Thêm mới tem
          </button>
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
                      <label for="checkbox-all" class="sr-only">checkbox</label>
                    </div>
                  </th>
                  <th
                    scope="col"
                    class="p-4 text-xs font-medium text-left text-gray-500 uppercase dark:text-gray-400"
                  >
                    Mã sản phẩm
                  </th>
                  <th
                    scope="col"
                    class="p-4 text-xs font-medium text-left text-gray-500 uppercase dark:text-gray-400"
                  >
                    Tên sản phẩm đóng gói
                  </th>
                  <th
                    scope="col"
                    class="p-4 text-xs font-medium text-left text-gray-500 uppercase dark:text-gray-400"
                  >
                    Sổ ghi chép sản xuất
                  </th>
                  <th
                    scope="col"
                    class="p-4 text-xs font-medium text-left text-gray-500 uppercase dark:text-gray-400"
                  >
                    Số lượng
                  </th>
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
                  v-for="(packageDto, index) in packages"
                  :key="index"
                >
                  <td class="w-4 p-4">
                    <div class="flex items-center">
                      <input
                        id="checkbox-{{ .id }}"
                        aria-describedby="checkbox-1"
                        type="checkbox"
                        class="w-4 h-4 border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-primary-300 dark:focus:ring-primary-600 dark:ring-offset-gray-800 dark:bg-gray-700 dark:border-gray-600"
                      />
                      <label for="checkbox-{{ .id }}" class="sr-only"
                        >checkbox</label
                      >
                    </div>
                  </td>
                  <td
                    class="p-4 text-base font-medium text-gray-900 whitespace-nowrap dark:text-white"
                  >
                    {{ packageDto.sku }}
                  </td>
                  <td
                    class="p-4 text-base font-medium text-gray-900 whitespace-nowrap dark:text-white"
                  >
                    {{ packageDto.productName }}
                  </td>
                  <td
                    class="p-4 text-base font-medium text-gray-900 whitespace-nowrap dark:text-white"
                  >
                    {{ packageDto.diary.name }}
                  </td>
                  <td
                    class="max-w-sm p-4 overflow-hidden text-base font-normal text-gray-500 truncate xl:max-w-xs dark:text-gray-400"
                  >
                    {{ packageDto.quantity }} {{ packageDto.basicUnit }}
                  </td>
                  <td class="p-4 space-x-2 whitespace-nowrap">
                    <button
                      type="button"
                      @click="viewDetail(packageDto.sku)"
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
</template>

<script>
import Pagination from "../pagination/Pagination.vue";
import PackageService from "../../services/PackageService";
const packageService = new PackageService();

export default {
  components: {
    Pagination,
  },
  data() {
    return {
      page: {
        totalPages: 1,
        totalItems: window.VUE_APP_TOTAL_ITEMS,
        currentPage: 1,
      },
      packages: [],
    };
  },
  async created() {
    await this.getAll();
  },
  methods: {
    createPackaging() {
      this.$router.push({ path: "/dashboard/packages/create" });
    },
    async onPageChange(page) {
      this.page.currentPage = page;
      this.getAll();
    },
    async getAll() {
      const res = await packageService.getAll({
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
        this.packages = data;
      }
    },
    viewDetail(sku) {
      this.$router.push({ path: `/dashboard/packages/${sku}` });
    },
  },
};
</script>

<style></style>
