<template>
  <div id="app">
    <NavBar />
    <div class="flex items-center justify-between pt-20 bg-gray-50 dark:bg-gray-900">
      <router-view />
    </div>
  </div>
</template>
<script>
import NavBar from "./views/NavBar.vue";

export default {
  components: {
    NavBar
  },
};
</script>
<style lang="scss"></style>
