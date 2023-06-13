import { createRouter, createWebHistory } from "vue-router";
import Home from "../views/Home.vue";
import DashBoard from "../views/DashBoard.vue";
import DashBoardHome from "../components/dashboard/Home.vue";

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/sign-in",
    name: "SignIn",
    component: () => import("../views/SignIn.vue"),
  },
  {
    path: "/sign-up",
    name: "SignUp",
    component: () => import("../views/SignUp.vue"),
  },
  {
    path: "/dashboard",
    name: "DashBoard",
    component: DashBoard,
    children: [
      {
        path: "home",
        component: DashBoardHome,
      },
      {
        path: "products",
        component: () => import("../components/product/Home.vue"),
      },
      {
        path: "products/create",
        component: () => import("../components/product/Create.vue"),
      },
      {
        path: "ingredients/:id",
        component: () => import("../components/ingredient/Create.vue"),
      },
      {
        path: "ingredients",
        component: () => import("../components/ingredient/Home.vue"),
      },
      {
        path: "ingredients/create",
        component: () => import("../components/ingredient/Create.vue"),
      },
      {
        path: "raw-material/info",
        component: () => import("../components/raw-material/Home.vue"),
      },
      {
        path: "diaries",
        component: () => import("../components/diary/Home.vue"),
      },
      {
        path: "diaries/:id",
        component: () => import("../components/diary/Create.vue"),
      },
      {
        path: "stamps",
        component: () => import("../components/stamp/Home.vue"),
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
