<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div
    class="flex flex-col items-center justify-center px-6 mx-auto pt:mt-0 w-[50%] h-[calc(100vh-80px)] dark:bg-gray-900"
  >
    <!-- <a
      href=""
      class="flex items-center justify-center mb-8 text-2xl font-semibold lg:mb-10 dark:text-white"
    >
      <img
        src="https://flowbite.com/docs/images/logo.svg"
        class="mr-4 h-11"
        alt="FlowBite Logo"
      />
      <span>Rice</span>
    </a> -->
    <!-- Card -->
    <div
      class="w-full max-w-xl p-6 space-y-8 sm:p-8 bg-white rounded-lg shadow dark:bg-gray-800"
    >
      <h2 class="text-2xl font-bold text-gray-900 dark:text-white">
        Đăng ký
      </h2>
      <form class="mt-8 space-y-6" action="#">
        <div>
          <label
            for="email"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Tài khoản</label
          >
          <input
            type="email"
            name="email"
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
            >Mật khẩu</label
          >
          <input
            type="password"
            name="password"
            id="password"
            placeholder="••••••••"
            class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            required
          />
        </div>
        <div>
          <label
            for="confirm-password"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >Nhập lại mật khẩu</label
          >
          <input
            type="password"
            name="confirm-password"
            id="confirm-password"
            placeholder="••••••••"
            class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            required
          />
        </div>
        <div>
          <h3 class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
            Đăng ký với vai trò
          </h3>
          <ul
            class="items-center w-full text-sm font-medium text-gray-900 bg-white border border-gray-200 rounded-lg sm:flex dark:bg-gray-700 dark:border-gray-600 dark:text-white"
          >
            <li
              class="w-full border-b border-gray-200 sm:border-b-0 sm:border-r dark:border-gray-600"
            >
              <div class="flex items-center pl-3">
                <input
                  id="horizontal-list-radio-license"
                  type="radio"
                  value=""
                  name="list-radio"
                  class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500"
                />
                <label
                  for="horizontal-list-radio-license"
                  class="w-full py-3 ml-2 text-sm font-medium text-gray-900 dark:text-gray-300"
                  >Nhà sản xuất
                </label>
              </div>
            </li>
            <li
              class="w-full border-b border-gray-200 sm:border-b-0 sm:border-r dark:border-gray-600"
            >
              <div class="flex items-center pl-3">
                <input
                  id="horizontal-list-radio-id"
                  type="radio"
                  value=""
                  name="list-radio"
                  class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500"
                />
                <label
                  for="horizontal-list-radio-id"
                  class="w-full py-3 ml-2 text-sm font-medium text-gray-900 dark:text-gray-300"
                  >Nhà cung cấp</label
                >
              </div>
            </li>
            <li
              class="w-full border-b border-gray-200 sm:border-b-0 sm:border-r dark:border-gray-600"
            >
              <div class="flex items-center pl-3">
                <input
                  id="horizontal-list-radio-millitary"
                  type="radio"
                  value=""
                  name="list-radio"
                  class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500"
                />
                <label
                  for="horizontal-list-radio-millitary"
                  class="w-full py-3 ml-2 text-sm font-medium text-gray-900 dark:text-gray-300"
                  >Nhà phân phối</label
                >
              </div>
            </li>
          </ul>
        </div>
        <button
          type="submit"
          class="w-full px-5 py-3 text-base font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 sm:w-auto dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
        >
          Tạo tài khoản
        </button>
        <div class="text-sm font-medium text-gray-500 dark:text-gray-400">
          Đã có tài khoản?
          <router-link
            :to="{ path: '/sign-in' }"
            class="text-blue-700 hover:underline dark:text-blue-500"
            >Đăng nhập tại đây</router-link
          >
        </div>
      </form>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style></style>
