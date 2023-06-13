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
          <h1
            class="text-xl font-semibold text-gray-900 sm:text-2xl dark:text-white"
          >
            Chi tiết sổ ghi chép
          </h1>
        </div>
        
      </div>
    </div>
    <div class="flex flex-row bg-white">
      <aside id="sidebar-multi-level-sidebar" class="w-64" aria-label="Sidebar">
        <div class="h-full px-3 py-4">
          <!-- <ul class="space-y-2 font-medium">
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
          </ul> -->
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
          <time
            class="block mb-2 text-sm font-normal leading-none text-gray-400 dark:text-gray-500"
            >Cập nhật lúc {{ item.createdAt }}</time
          >
          <p
            class="mb-4 text-base font-normal text-gray-500 dark:text-gray-400"
          >
            {{ item.description }}
          </p>
          <div
            v-for="index in 3"
            style="width: 100px; height: 100px"
            class="relative mt-3 ml-3 border-2 shadow-xl rounded-md inline-block"
            :key="index"
          >
            <img
              class="object-cover w-full h-full"
              src="https://co4la.vn/wp-content/uploads/2022/05/anh-dai-dien-co-4-la-1.jpg"
            />
          </div>
        </li>
      </ol>

      <section class="w-[50%] bg-white dark:bg-gray-900">
        <div class="py-8 px-4 mx-auto max-w-2xl lg:py-16">
          <h2 class="mb-4 text-xl font-bold text-gray-900 dark:text-white">
            Viết ghi chép sản xuất
          </h2>
          <form action="#">
            <div class="grid gap-4">
              <div class="w-full">
                <label
                  for="name"
                  class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                  >Giai đoạn</label
                >
                <select
                  id="category"
                  v-model="newDiary.step"
                  class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-500 focus:border-primary-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-primary-500 dark:focus:border-primary-500"
                >
                  <option selected=""></option>
                  <option value="SOAK_SEED">Ngâm giống/Monitors</option>
                  <option value="SEEDING">Gieo trồng</option>
                  <option value="FERTILIZATION">Bón phân</option>
                  <option value="TAKE_CART_OF">Chăm sóc</option>
                  <option value="HARVESTING">Thu hoạch</option>
                </select>
              </div>
              <div class="w-full">
                <label
                  for="name"
                  class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                  >Nguyên liệu</label
                >
                <select
                  id="category"
                  v-model="newDiary.ingredientId"
                  class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-500 focus:border-primary-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-primary-500 dark:focus:border-primary-500"
                >
                  <option selected=""></option>
                  <option
                    v-for="(item, index) in ingredients"
                    :key="index"
                    :value="item.id"
                  >
                    {{ item.name }}
                  </option>
                </select>
              </div>
              <div class="w-full mb-2">
                <label
                  for="description"
                  class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                  >Ghi chú</label
                >
                <textarea
                  id="description"
                  rows="8"
                  v-model="newDiary.description"
                  class="block p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-primary-500 focus:border-primary-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-primary-500 dark:focus:border-primary-500"
                  placeholder="Your description here"
                ></textarea>
              </div>
            </div>
            <div class="w-full">
              <label
                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >Hình ảnh</label
              >
              <FileUpload name="images" />
            </div>

            <button
              @click="createDetailDiary"
              id="createProductButton"
              class="mt-4 text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
              type="button"
            >
              Ghi chép
            </button>
          </form>
        </div>
      </section>
    </div>
  </div>
</template>

<script>
import IngredientService from "../../services/IngredientService";
import DiaryService from "../../services/DiaryService";
import FileUpload from "../file/FileUpload.vue";
import DiaryStep from "../../enums/DiaryStep";
const ingredientService = new IngredientService();
const diaryService = new DiaryService();

export default {
  components: {
    FileUpload,
  },
  data() {
    return {
      newDiary: {
        step: "",
        description: "",
        createdAt: "2023-05-07T15:13:50.993Z",
        diaryId: 0,
        ingredientId: 0,
      },
      diary: {},
      ingredients: [],
    };
  },
  async created() {
    const id = this.$route.params.id;
    this.newDiary.diaryId = id;
    await Promise.all([this.getDetailDiary(id), this.getAllIngredients()]);
  },
  methods: {
    async getAllIngredients() {
      const res = await ingredientService.getAllWithoutPaging();

      if (res.status === 200) {
        this.ingredients = res.data;
        console.log(this.ingredients);
      }
    },
    async createDetailDiary() {
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

      const res = await diaryService.add(this.newDiary, imageBlobs);

      if (res.status === 200) {
        localStorage.removeItem("images");
      }
    },
    async getDetailDiary(id) {
      const res = await diaryService.detail(id);

      if (res.status === 200) {
        this.diary = res.data;
      }
    },
    getStep(step) {
      return DiaryStep[step];
    },
  },
};
</script>

<style></style>
