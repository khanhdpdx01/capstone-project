<template>
  <div
    class="relative w-full h-full overflow-y-auto bg-gray-50 lg:ml-64 dark:bg-gray-900"
  >
    <SideBar />

    <router-view />
  </div>
</template>

<script>
import SideBar from "./SideBar.vue";

export default {
  components: { SideBar },
};
</script>

<style></style>
