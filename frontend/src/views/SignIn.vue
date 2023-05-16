<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div
    class="flex flex-col items-center justify-center px-6 pt-8 mx-auto pt:mt-0 w-[50%] h-[calc(100vh-80px)] dark:bg-gray-900"
  >
    <a
      href=""
      class="flex items-center justify-center mb-8 text-2xl font-semibold lg:mb-10 dark:text-white"
    >
      <img
        src="https://flowbite.com/docs/images/logo.svg"
        class="mr-4 h-11"
        alt="FlowBite Logo"
      />
      <span>Rice</span>
    </a>
    <!-- Card -->
    <div
      class="w-full max-w-xl p-6 space-y-8 sm:p-8 bg-white rounded-lg shadow dark:bg-gray-800"
    >
      <h2 class="text-2xl font-bold text-gray-900 dark:text-white">
        Sign in to platform
      </h2>
      <form class="mt-8 space-y-6" submit.prevent>
        <div>
          <label
            for="email"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Your email</label
          >
          <input
            type="text"
            name="email"
            v-model="data.username"
            id="email"
            class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            placeholder="name@company.com"
            required
          />
        </div>
        <div>
          <label
            for="password"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Your password</label
          >
          <input
            type="password"
            name="password"
            id="password"
            v-model="data.password"
            placeholder="••••••••"
            class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            required
          />
        </div>
        <div class="flex items-start">
          <div class="flex items-center h-5">
            <input
              id="remember"
              aria-describedby="remember"
              name="remember"
              type="checkbox"
              class="w-4 h-4 border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-blue-300 dark:focus:ring-blue-600 dark:ring-offset-gray-800 dark:bg-gray-700 dark:border-gray-600"
              required
            />
          </div>
          <div class="ml-3 text-sm">
            <label
              for="remember"
              class="font-medium text-gray-900 dark:text-white"
              >Remember me</label
            >
          </div>
          <a
            href="#"
            class="ml-auto text-sm text-blue-700 hover:underline dark:text-blue-500"
            >Lost Password?</a
          >
        </div>
        <button
          @click="login"
          type="button"
          class="w-full px-5 py-3 text-base font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 sm:w-auto dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
        >
          Login to your account
        </button>
        <div class="text-sm font-medium text-gray-500 dark:text-gray-400">
          Not registered?
          <router-link
            :to="{ path: '/sign-up' }"
            class="text-blue-700 hover:underline dark:text-blue-500"
            >Create account</router-link
          >
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import AuthService from "../services/AuthService";
const authService = new AuthService();

export default {
  data() {
    return {
      data: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    async login() {
      const res = await authService.login(this.data);

      if (res.status === 200) {
        localStorage.setItem("access_token", res.data.accessToken);
        this.$router.push({ path: "/dashboard/home" });
      }
    },
  },
};
</script>

<style></style>
