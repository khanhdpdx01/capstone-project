<template>
  <nav
    class="fixed top-0 z-50 w-full h-[80px] bg-white border-b border-gray-200 dark:bg-gray-800 dark:border-gray-700"
  >
    <div class="h-full px-3 py-3 lg:px-5 lg:pl-3">
      <div class="h-full flex items-center justify-between">
        <div class="flex items-center justify-start">
          <button
            data-drawer-target="logo-sidebar"
            data-drawer-toggle="logo-sidebar"
            aria-controls="logo-sidebar"
            type="button"
            class="inline-flex items-center p-2 text-sm text-gray-500 rounded-lg md:hidden hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200 dark:text-gray-400 dark:hover:bg-gray-700 dark:focus:ring-gray-600"
          >
            <span class="sr-only">Open sidebar</span>
            <svg
              class="w-6 h-6"
              aria-hidden="true"
              fill="currentColor"
              viewBox="0 0 20 20"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                clip-rule="evenodd"
                fill-rule="evenodd"
                d="M2 4.75A.75.75 0 012.75 4h14.5a.75.75 0 010 1.5H2.75A.75.75 0 012 4.75zm0 10.5a.75.75 0 01.75-.75h7.5a.75.75 0 010 1.5h-7.5a.75.75 0 01-.75-.75zM2 10a.75.75 0 01.75-.75h14.5a.75.75 0 010 1.5H2.75A.75.75 0 012 10z"
              ></path>
            </svg>
          </button>
          <router-link :to="{ path: '/' }" class="flex ml-2 md:mr-24">
            <!-- <img
              src="https://flowbite.com/docs/images/logo.svg"
              class="h-8 mr-3"
              alt="FlowBite Logo"
            /> -->
            <span
              class="self-center text-xl font-semibold sm:text-2xl whitespace-nowrap dark:text-white"
              >Rice</span
            >
          </router-link>
        </div>
        <div class="flex items-center">
          <div v-if="hasLogin">
            <div>
              <button
                type="button"
                class="flex text-sm bg-gray-800 rounded-full focus:ring-4 focus:ring-gray-300 dark:focus:ring-gray-600"
                aria-expanded="false"
                data-dropdown-toggle="dropdown-user"
              >
                <span class="sr-only">Open user menu</span>
                <img
                  class="w-8 h-8 rounded-full"
                  src="https://flowbite.com/docs/images/people/profile-picture-5.jpg"
                  alt="user photo"
                />
              </button>
            </div>
            <div
              class="z-50 hidden my-4 text-base list-none bg-white divide-y divide-gray-100 rounded shadow dark:bg-gray-700 dark:divide-gray-600"
              id="dropdown-user"
            >
              <div class="px-4 py-3" role="none">
                <p class="text-sm text-gray-900 dark:text-white" role="none">
                  Khanh Nguyen
                </p>
                <p
                  class="text-sm font-medium text-gray-900 truncate dark:text-gray-300"
                  role="none"
                >
                  khanhdpdx@gmail.com
                </p>
              </div>
              <ul class="py-1" role="none">
                <li>
                  <router-link
                    :to="{ path: '/dashboard/user' }"
                    class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:text-gray-300 dark:hover:bg-gray-600 dark:hover:text-white"
                    role="menuitem"
                    >Thông tin doanh nghiệp
                  </router-link>
                </li>
                <li>
                  <a
                    @click="signOut"
                    class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:text-gray-300 dark:hover:bg-gray-600 dark:hover:text-white"
                    role="menuitem"
                    >Đăng xuất</a
                  >
                </li>
              </ul>
            </div>
          </div>
          <div v-else class="md:flex text-lg">
            <!-- <a href="#" class="text-gray-800 hover:text-purple-300 py-3 px-6"
              >Home</a
            >
            <a href="#" class="text-gray-800 hover:text-purple-300 py-3 px-6"
              >Services</a
            >
            <a href="#" class="text-gray-800 hover:text-purple-300 py-3 px-6"
              >About</a
            >
            <a href="#" class="text-gray-800 hover:text-purple-300 py-3 px-6"
              >Contact</a
            >
            <a href="#" class="text-gray-800 hover:text-purple-300 py-3 px-6"
              >FAQ</a
            > -->
            <router-link
              :to="{ path: '/sign-in' }"
              class="bg-purple-200 hover:bg-purple-300 rounded-full uppercase text-purple-700 py-3 px-6"
              >Sign In</router-link
            >
          </div>
        </div>
      </div>
    </div>
  </nav>
</template>

<script>
import { Modal } from "flowbite";
import { mapGetters } from "vuex";

export default {
  data() {
    return {
      isLogin: false,
    };
  },
  computed: {
    ...mapGetters("auth", ["hasLogin"]),
  },
  methods: {
    showProfileDropdown() {
      const ref = document.getElementById("dropdown-user");
      const modal = new Modal(ref);
      modal.show();
    },
    signOut() {
      localStorage.removeItem("vuex");
      this.$router.push({ path: "/" }).catch(() => {});
    },
  },
};
</script>

<style></style>
