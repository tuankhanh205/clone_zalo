import { createRouter, createWebHistory } from 'vue-router';
import { routes } from './Router';

const router = createRouter({
  history: createWebHistory(),
  routes
});

// Middleware kiểm tra token
router.beforeEach((to, _, next) => {
  const token = localStorage.getItem('token');
  if (to.meta.requiresAuth && !token) {
    next('/login');
  } else {
    next();
  }
});

export default router;
