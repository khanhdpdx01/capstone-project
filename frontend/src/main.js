import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "./assets/tailwind.css";
import Toast from "vue-toastification";
import "vue-toastification/dist/index.css";
import setUpInterceptor from "./services/setUpInterceptor";

setUpInterceptor(router, store);

const options = { timeout: 1500 };

createApp(App).use(store).use(router).use(Toast, options).mount("#app");
