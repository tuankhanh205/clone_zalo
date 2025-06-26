import type { RouteRecordRaw } from 'vue-router';

export const routes: RouteRecordRaw[] = [
    {
    path: '/login',
    component: () => import('../views/Login.vue')
  },
  {
    
    path: '/',
    name: 'Home',
    component: () => import('../views/Home.vue'),
    meta: { icon: '📨', title: 'Tin nhắn' }
  },
  {
    path: '/contacts',
    name: 'Contacts',
    component: () => import('../views/Contacts.vue'),
    meta: { icon: '👥', title: 'Danh bạ' }
  }
  
];
