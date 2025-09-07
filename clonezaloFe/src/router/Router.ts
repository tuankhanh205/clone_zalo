import { createRouter, createWebHistory } from "vue-router";
import MainLayout from "../layout/MainLayout.vue";
import Login from "../views/Login.vue";
import GioiThieu from "../views/gioiThieu.vue";

const routes = [
  {
    path: "/",
    name: "main",
    component: MainLayout,
  },
  {
    path: "/dangxuat",
    name: "dangxuat",
    component: Login,
  },
    {
    path: "/contac",
    name: "Contacts",
    component: Login,
  },
  {
    path: "/gioithieu",
    name: "gioithieu",
    component: GioiThieu,
  },
  
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
