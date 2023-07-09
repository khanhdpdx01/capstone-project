<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div class="w-full pt-8 bg-white">
    <div class="px-[250px] sm:px-0 sm:m-4">
      <div class="lg:grid lg:grid-cols-3 lg:gap-4">
        <div>
          <img
            className="lg:w-[450px] lg:h-[400px] rounded-[25px] sm:w-[w-full] sm:h-[full]"
            :src="`http://localhost:3000/files/${packageDto.imagePath}`"
          />
          <div
            className="lg:w-[450px] mt-4 py-4 px-8 border rounded-xl border-neutral-300 border-opacity-50"
          >
            <h1 className="text-center text-slate-700 text-[28px] font-bold">
              {{ packageDto.productName }}
            </h1>
            <div class="mb-4">
              <h3 class="font-semibold text-gray-900">Ngày sản xuất</h3>
              <span>{{ formatVNDate(packageDto.packageDate) }}</span>
            </div>
            <div class="mb-4">
              <h3 class="font-semibold text-gray-900">Hạn sử dụng</h3>
              <span>{{ formatVNDate(packageDto.expiryDate) }}</span>
            </div>
            <div class="mb-4">
              <h3 class="font-semibold text-gray-900">Địa chỉ sản xuất</h3>
              <span>{{ packageDto.address }}</span>
            </div>
          </div>
        </div>
        <div class="lg:col-span-2 sm:mt-4">
          <!-- <ul class="lg:h-20 lg:grid lg:grid-cols-3 opacity-80 bg-slate-50 rounded-lg">
            <li class="w-full h-full flex justify-center items-center">
              <span class="text-slate-700 text-[18px] font-bold"
                >Thông tin sản phẩm</span
              >
            </li>
            <li
              class="w-full h-full flex justify-center items-center bg-white rounded-lg shadow"
            >
              <span class="text-slate-700 text-[18px] font-bold"
                >Truy xuất nguồn gốc</span
              >
            </li>
            <li class="w-full h-full flex justify-center items-center">
              <span class="text-slate-700 text-[18px] font-bold"
                >Thông tin công ty</span
              >
            </li>
          </ul> -->
          <div
            class="mt-4 border rounded-xl border-neutral-300 border-opacity-50"
          >
            <div class="m-4 text-slate-700 text-[24px] font-semibold sm:text-[16px]">
              Thông tin truy xuất nguồn gốc
            </div>
            <div class="w-full">
              <div
                class="mx-10 sm:mx-4"
                v-for="(item, index) in diary.diaryDetails"
                :key="index"
              >
                <div class="flex items-baseline">
                  <svg
                    class="w-4 h-4 mr-4 mb-8 text-gray-800 dark:text-white"
                    aria-hidden="true"
                    xmlns="http://www.w3.org/2000/svg"
                    fill="none"
                    viewBox="0 0 14 8"
                  >
                    <path
                      stroke="currentColor"
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      stroke-width="2"
                      d="m1 1 5.326 5.7a.909.909 0 0 0 1.348 0L13 1"
                    />
                  </svg>
                  <span class="text-slate-700 text-[18px] font-semibold">
                    {{ getStep(item.step) }}</span
                  >
                </div>
                <div class="lg:flex">
                  <div class="flex flex-col sm:flex-row">
                    <span
                      class="text-center text-teal-900 text-[16px] font-semibold sm:mr-4"
                      >10:00</span
                    >
                    <div class="text-teal-900 text-[16px] font-normal">
                      {{ formatVNDate(item.createdAt) }}
                    </div>
                  </div>
                  <div class="sm:mt-4">
                    <div class="lg:ml-20">
                      <div
                       @click="viewDetailTransaction(item.transactionId)"
                        class="flex items-center mb-4 px-4 py-2 bg-green-200 rounded-[30px]"
                      >
                        <svg
                          width="29"
                          height="27"
                          viewBox="0 0 29 27"
                          fill="none"
                          xmlns="http://www.w3.org/2000/svg"
                        >
                          <g clip-path="url(#clip0_8352_91696)">
                            <path
                              fill-rule="evenodd"
                              clip-rule="evenodd"
                              d="M27.7082 3.29232C27.8013 3.38521 27.8752 3.49557 27.9256 3.61706C27.976 3.73855 28.002 3.86879 28.002 4.00032C28.002 4.13186 27.976 4.2621 27.9256 4.38359C27.8752 4.50508 27.8013 4.61543 27.7082 4.70832L13.7082 18.7083C13.6153 18.8015 13.5049 18.8753 13.3834 18.9257C13.2619 18.9762 13.1317 19.0021 13.0002 19.0021C12.8686 19.0021 12.7384 18.9762 12.6169 18.9257C12.4954 18.8753 12.3851 18.8015 12.2922 18.7083L6.29216 12.7083C6.19919 12.6153 6.12544 12.505 6.07512 12.3835C6.0248 12.262 5.9989 12.1318 5.9989 12.0003C5.9989 11.8688 6.0248 11.7386 6.07512 11.6172C6.12544 11.4957 6.19919 11.3853 6.29216 11.2923C6.38514 11.1993 6.49552 11.1256 6.617 11.0753C6.73848 11.025 6.86868 10.9991 7.00016 10.9991C7.13165 10.9991 7.26185 11.025 7.38333 11.0753C7.50481 11.1256 7.61519 11.1993 7.70817 11.2923L13.0002 16.5863L26.2922 3.29232C26.3851 3.1992 26.4954 3.12531 26.6169 3.0749C26.7384 3.02448 26.8686 2.99854 27.0002 2.99854C27.1317 2.99854 27.2619 3.02448 27.3834 3.0749C27.5049 3.12531 27.6153 3.1992 27.7082 3.29232Z"
                              fill="#2DC071"
                            />
                            <path
                              fill-rule="evenodd"
                              clip-rule="evenodd"
                              d="M13 3.00022C10.8244 3.00022 8.69769 3.64536 6.88875 4.85406C5.07981 6.06275 3.66991 7.78072 2.83734 9.79071C2.00478 11.8007 1.78694 14.0124 2.21138 16.1462C2.63582 18.28 3.68347 20.24 5.22184 21.7784C6.76022 23.3168 8.72023 24.3644 10.854 24.7889C12.9878 25.2133 15.1996 24.9955 17.2095 24.1629C19.2195 23.3303 20.9375 21.9204 22.1462 20.1115C23.3549 18.3026 24 16.1758 24 14.0002C24 13.735 24.1054 13.4807 24.2929 13.2931C24.4805 13.1056 24.7348 13.0002 25 13.0002C25.2652 13.0002 25.5196 13.1056 25.7071 13.2931C25.8947 13.4807 26 13.735 26 14.0002C25.9997 16.8619 25.0551 19.6435 23.3127 21.9136C21.5704 24.1837 19.1277 25.8155 16.3635 26.5559C13.5992 27.2963 10.6679 27.1039 8.02417 26.0086C5.38042 24.9133 3.17195 22.9762 1.74127 20.4978C0.310593 18.0195 -0.262343 15.1383 0.111317 12.3011C0.484977 9.46392 1.78435 6.82931 3.80793 4.80587C5.8315 2.78243 8.46619 1.48322 11.3034 1.10974C14.1406 0.736265 17.0217 1.30939 19.5 2.74022C19.6195 2.80274 19.7252 2.88878 19.8105 2.99318C19.8959 3.09757 19.9593 3.21816 19.9969 3.34769C20.0345 3.47721 20.0455 3.61301 20.0292 3.74689C20.0129 3.88078 19.9698 4.00999 19.9023 4.12675C19.8348 4.24352 19.7444 4.34542 19.6365 4.42634C19.5286 4.50725 19.4054 4.56551 19.2744 4.59759C19.1434 4.62968 19.0073 4.63494 18.8742 4.61305C18.7411 4.59116 18.6138 4.54258 18.5 4.47022C16.8285 3.5036 14.9309 2.99644 13 3.00022Z"
                              fill="#2DC071"
                            />
                          </g>
                          <defs>
                            <clipPath id="clip0_8352_91696">
                              <rect width="29" height="27" fill="white" />
                            </clipPath>
                          </defs>
                        </svg>
                        <div
                          class="ml-4 text-green-500 text-[16px] font-medium leading-tight"
                        >
                          Thông tin được lưu và xác thực bởi Blockchain
                        </div>
                      </div>
                      <div class="mb-4">
                        <h3 class="font-semibold text-gray-900">
                          Người thực hiện
                        </h3>
                        <span>ST25</span>
                      </div>
                      <div class="mb-4">
                        <h3 class="font-semibold text-gray-900">Nguyên liệu</h3>
                        <span>ST25</span>
                      </div>
                      <div class="mb-4">
                        <h3 class="font-semibold text-gray-900">Ghi chú</h3>
                        <span>{{ item.description }}</span>
                      </div>
                      <div class="mb-4">
                        <h3 class="font-semibold text-gray-900">Hình ảnh</h3>
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
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import PackageService from "../../services/PackageService";
import DiaryStep from "../../enums/DiaryStep";
const packageService = new PackageService();

export default {
  data() {
    return {
      diary: {},
      packageDto: {},
      stamp: {},
    };
  },
  async created() {
    const id = this.$route.params.id;
    await this.trace(id);
  },
  methods: {
    async trace(id) {
      const res = await packageService.trace(id);

      if (res.status === 200) {
        this.diary = res.data.diaryDto;
        this.packageDto = res.data.packageProduct;
        this.stamp = res.data.stamp;
      }
    },
    getStep(step) {
      return DiaryStep[step];
    },
    formatVNDate(date) {
      return new Date(date).toLocaleDateString("vi-VN");
    },
  },
};
</script>

<style></style>
