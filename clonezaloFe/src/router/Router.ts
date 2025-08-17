// import { createRouter, createWebHistory } from 'vue-router'
// import type { RouteRecordRaw } from 'vue-router'

// const routes: RouteRecordRaw[] = [
//   // Layout dành cho auth (login, register...)
//   {
//     path: '/auth',
//     component: () => import('../layout/AuthLayout.vue'),
//     children: [
//       {
//         path: 'login',
//         name: 'Login',
//         component: () => import('../views/Login.vue')
//       },
//       {
//         path: 'register',
//         name: 'Register',
//         component: () => import('../views/Regisster.vue')
//       }
//     ]
//   },

//   // Layout chính (có sidebar)
//   {
//     path: '/',
//     component: () => import('../layout/DefaultLayout.vue'),
//     meta: { requiresAuth: true },
//     children: [
//       {
//         path: '',
//         name: 'Home',
//         component: () => import('../views/Home.vue'),
//         meta: { icon: '📨', title: 'Tin nhắn' }
//       },
//       {
//         path: 'contacts',
//         name: 'Contacts',
//         component: () => import('../views/Contacts.vue'),
//         meta: { icon: '👥', title: 'Danh bạ' }
//       }
//     ]
//   }
// ]

// const router = createRouter({
//   history: createWebHistory(),
//   routes
// })

// // 🔑 Global Guard: Kiểm tra login
// router.beforeEach((to, from, next) => {
//   const token = localStorage.getItem('token')
//   if (to.meta.requiresAuth && !token) {
//     next('/auth/login')
//   } else {
//     next()
//   }
// })

// export default router
