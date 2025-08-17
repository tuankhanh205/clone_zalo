import { createRouter, createWebHistory } from 'vue-router'

export const routes = [
  {
    path: '/auth',
    component: () => import('../layout/AuthLayout.vue'),
    children: [
      { 
        path: 'login', 
        name: 'Login', 
        component: () => import('../views/Login.vue'),
        meta: { 
          title: 'Đăng nhập',
          icon: '🔐',
          showInMenu: false
        } 
      },
       { 
        path: 'register', 
        name: 'Register', 
        component: () => import('../views/Regisster.vue'),
        meta: { 
          title: 'Đăng nhập',
          icon: '🔐',
          showInMenu: false
        } 
      }
    ]
  },
  {
    path: '/',
    component: () => import('../layout/DefaultLayout.vue'),
    children: [
      { 
        path: '', 
        name: 'Home', 
        component: () => import('../views/Home.vue'),
        meta: {
          title: 'Trang chủ',
          icon: '🏠',
          showInMenu: true,
          requiresAuth: true // thêm trường này để bảo vệ route
        }
      },
      { 
        path: 'contacts', 
        name: 'Contacts', 
        component: () => import('../views/Contacts.vue'),
        meta: {
          title: 'Danh bạ',
          icon: '📞',
          showInMenu: true,
          requiresAuth: true
        }
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// Router guard kiểm tra login
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token')
  if (to.meta.requiresAuth && !token) {
    // Nếu route cần auth mà chưa login → redirect login
    next({ path: '/auth/login' })
  } else {
    next() // cho phép vào route
  }
})

export default router
