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
                  Sổ ghi chép
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
                    >Chi tiết sổ ghi chép</a
                  >
                </div>
              </li>
            </ol>
          </nav>
          <div class="flex justify-between">
            <h1
              class="text-xl font-semibold text-gray-900 sm:text-2xl dark:text-white"
            >
              Chi tiết sổ ghi chép
            </h1>
            <button
              @click="createDetailDiary"
              class="text-white bg-red-700 hover:bg-red-800 focus:ring-4 focus:ring-red-300 font-medium rounded-lg text-sm px-5 py-2.5"
              type="button"
            >
              Viết ghi chép
            </button>
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
              <h3 class="font-semibold text-gray-900">Tên sổ ghi chép</h3>
              <span>{{ diary.name }}</span>
            </div>
            <div>
              <h3 class="font-semibold text-gray-900">Tên sản phẩm</h3>
              <span>{{ product.name }}</span>
            </div>
            <div>
              <h3 class="font-semibold text-gray-900">Trạng thái ghi chép</h3>
              <span>{{ getStatus(diary.status) }}</span>
            </div>
            <div>
              <h3 class="font-semibold text-gray-900">Thời gian tạo</h3>
              <span>{{ formatVNDate(diary.createdAt) }}</span>
            </div>
          </div>
        </div>
      </div>

      <div>
        <h2 class="pt-4 text-xl font-semibold text-gray-900">
          Chi tiết sổ ghi chép
        </h2>
        <div class="flex flex-row">
          <aside
            id="sidebar-multi-level-sidebar"
            class="w-64"
            aria-label="Sidebar"
          >
            <div class="h-full px-3 py-4">
              <ul class="space-y-2 font-medium">
                <li>
                  <a
                    href="#"
                    class="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700"
                  >
                    <span class="ml-3">Ngâm giống</span>
                  </a>
                </li>
                <li>
                  <a
                    href="#"
                    class="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700"
                  >
                    <span class="ml-3">Gieo hạt</span>
                  </a>
                </li>
                <li>
                  <a
                    href="#"
                    class="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700"
                  >
                    <span class="ml-3">Bón phân</span>
                  </a>
                </li>
                <li>
                  <a
                    href="#"
                    class="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700"
                  >
                    <span class="ml-3">Chăm sóc</span>
                  </a>
                </li>
                <li>
                  <a
                    href="#"
                    class="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700"
                  >
                    <span class="ml-3">Thu hoạch</span>
                  </a>
                </li>
              </ul>
            </div>
          </aside>

          <ol
            class="relative bg-white border-l border-r border-gray-200 dark:border-gray-700 pr-4 w-[50%]"
          >
            <li
              class="mb-10 ml-6"
              v-for="(item, index) in diary.diaryDetails"
              :key="index"
            >
              <span
                class="absolute flex items-center justify-center w-6 h-6 bg-blue-100 rounded-full -left-3 ring-8 ring-white dark:ring-gray-900 dark:bg-blue-900"
              >
                <svg
                  aria-hidden="true"
                  class="w-3 h-3 text-blue-800 dark:text-blue-300"
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
              </span>
              <h3
                class="flex items-center mb-1 text-lg font-semibold text-gray-900 dark:text-white"
              >
                {{ getStep(item.step) }}
              </h3>
              <p
                class="mb-4 text-base font-normal text-gray-500 dark:text-gray-400"
              >
                Nguyên liệu: {{ item.ingredient.name }}
              </p>
              <p
                class="mb-4 text-base font-normal text-gray-500 dark:text-gray-400"
              >
                Ghi chú: {{ item.description }}
              </p>
              <time
                class="block mb-2 text-sm font-normal leading-none text-gray-400 dark:text-gray-500"
                >Ghi chép lúc {{ formatVNDate(item.createdAt) }}</time
              >
              
              <div
                v-for="(hash, index) in item.imagePath.split(',')"
                style="width: 100px; height: 100px"
                class="relative mt-3 ml-3 border-2 shadow-xl rounded-md inline-block"
                :key="index"
              >
                <img
                  class="object-cover w-full h-full"
                  :src="`http://localhost:3000/files/${hash}`"
                />
              </div>
            </li>
          </ol>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import DiaryService from "../../services/DiaryService";
import ProductService from "../../services/ProductService";
import DiaryStep from "../../enums/DiaryStep";
import DiaryStatus from "../../enums/DiaryStatus";
const diaryService = new DiaryService();
const productService = new ProductService();

export default {
  data() {
    return {
      diary: {},
      product: {}
    };
  },
  async created() {
    const id = this.$route.params.id;
    await this.getDetailDiary(id);
  },
  methods: {
    async getDetailDiary(id) {
      const res = await diaryService.detail(id);

      if (res.status === 200) {
        this.diary = res.data;
        this.sortDiary(this.diary);
      }

      const res1 = await productService.detail(this.diary.productId);
      if (res1.status === 200) {
        this.product = res1.data;
      }
    },
    getStep(step) {
      return DiaryStep[step];
    },
    sortDiary(diary) {
      const sortedDiary = {};

      diary.diaryDetails.forEach((diaryDetail) => {
        if (sortedDiary[diaryDetail.step]) {
          sortedDiary[diaryDetail.step].push(diaryDetail);
        } else {
          sortedDiary[diaryDetail.step] = [diaryDetail];
        }
      });

      for (const step in DiaryStep) {
        if (sortedDiary[step]) {
          sortedDiary[step].sort((date1, date2) => {
            return new Date(date1) - new Date(date2);
          });
        }
      }
      console.log(sortedDiary);
    },
    createDetailDiary() {
      this.$router.push({
        path: `/dashboard/diaries/create/${this.$route.params.id}`,
      });
    },
    formatVNDate(date) {
      return new Date(date).toLocaleDateString("vi-VN");
    },
    getStatus(status) {
      return DiaryStatus[status];
    },
  },
};
</script>

<style></style>
