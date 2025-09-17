import { createRouter, createWebHistory } from "vue-router";
import MainLayout from "../layout/MainLayout.vue";
import Login from "../views/Login.vue";
import GioiThieu from "../views/gioiThieu.vue";
import Regisster from "../views/Regisster.vue";

const routes = [
  {
    path: "/",
    name: "main",
    component: MainLayout,
    meta: { requiresAuth: true } // Yêu cầu xác thực
  },
  {
    path: "/dangxuat",
    name: "dangxuat",
    component: Login
  },
  {
    path: "/contac",
    name: "Contacts",
    component: Login
  },
  {
    path: "/gioithieu",
    name: "gioithieu",
    component: GioiThieu,
    meta: { requiresAuth: true } // Yêu cầu xác thực
  },
  {
    path: "/login",
    name: "login",
    component: Login
  },
   {
    path: "/register",
    name: "register",
    component: Regisster
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

// Hàm kiểm tra token có hợp lệ không
function isValidToken() {
  const token = localStorage.getItem('token');
  if (!token) return false;
  
  try {
    // Giả sử token là JWT, kiểm tra thời gian hết hạn
    const payload = JSON.parse(atob(token.split('.')[1]));
    const currentTime = Math.floor(Date.now() / 1000);
    
    // Kiểm tra nếu token chưa hết hạn
    return payload.exp > currentTime;
  } catch (error) {
    console.error('Token không hợp lệ:', error);
    return false;
  }
}

// Navigation guard để kiểm tra trước khi điều hướng
router.beforeEach((to, from, next) => {
  // Nếu route yêu cầu xác thực
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (!isValidToken()) {
      // Chuyển hướng đến trang login nếu không có token hoặc token hết hạn
      next({ name: 'login' });
    } else {
      next(); // Cho phép điều hướng
    }
  } else {
    next(); // Không yêu cầu xác thực, cho phép điều hướng
  }
});

export default router;