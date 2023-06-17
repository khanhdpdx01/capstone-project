<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div class="p-4 bg-white">
    <div class="flex justify-between">
      <h1 class="text-xl font-semibold text-gray-900 sm:text-2xl">
        Thông tin doanh nghiệp
      </h1>
      <button
        @click="updateUser"
        class="text-white bg-red-700 hover:bg-red-800 focus:ring-4 focus:ring-red-300 font-medium rounded-lg text-sm px-5 py-2.5"
        type="button"
      >
        Cập nhật thông tin
      </button>
    </div>
    <div class="grid grid-cols-4 gap-4 pt-10">
      <div class="flex flex-col items-center">
        <img src="../../assets/img/file.png" alt="" class="h-32 w-32" />
        <span class="mt-4">khanhdpdx@gmail.com</span>
        <span class="mt-4 p-4 text-red-600 bg-red-100 font-medium rounded-lg"
          >Nhà sản xuất</span
        >
      </div>
      <div class="col-span-3">
        <div class="mb-4">
          <h3 class="mb-2 text-xl font-semibold text-gray-900">Tên đơn vị</h3>
          <span class="text-lg">{{ user.fullName }}</span>
        </div>
        <div class="mb-4">
          <h3 class="mb-2 text-xl font-semibold text-gray-900">
            Số điện thoại
          </h3>
          <span class="text-lg">{{ user.phoneNumber }}</span>
        </div>
        <div class="mb-4">
          <h3 class="mb-2 text-xl font-semibold text-gray-900">Địa chỉ</h3>
          <span class="text-lg">{{ user.address }}</span>
        </div>
        <div class="mb-4">
          <h3 class="mb-2 text-xl font-semibold text-gray-900">Email đơn vị</h3>
          <span class="text-lg">{{ user.email }}</span>
        </div>
        <div class="mb-4">
          <h3 class="mb-2 text-xl font-semibold text-gray-900">Website</h3>
          <span class="text-lg">{{ user.website }}</span>
        </div>
        <div class="mb-4">
          <h3 class="mb-2 text-xl font-semibold text-gray-900">
            Mô tả về đơn vị
          </h3>
          <span class="text-lg">{{ user.description }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import UserService from "../../services/UserService";
import { mapGetters } from "vuex";
const userService = new UserService();

export default {
  data() {
    return {
      user: {},
    };
  },
  async created() {
    await this.getUser();
  },
  computed: {
    ...mapGetters("auth", ["getUserId"]),
  },
  methods: {
    async getUser() {
      const res = await userService.getUser(this.getUserId);

      if (res.status === 200) {
        this.user = res.data;

        for (const property in this.user) {
          if (this.user[property] === null) {
            this.user[property] = "Chưa cập nhật thông tin";
          }
        }
      }
    },
  },
};
</script>

<style></style>
