<template>
  <aside class="w-64 h-screen bg-white shadow-md p-4">
    <h1 class="text-2xl font-bold text-blue-600 mb-6">Zalo Clone</h1>
    
    <nav class="space-y-2">
      <RouterLink
        v-for="route in menuRoutes"
        :key="route.path"
        :to="route.path"
        class="block px-4 py-2 rounded hover:bg-blue-100 transition"
      >
        {{ route.meta?.icon }} {{ route.meta?.title }}
      </RouterLink>
    </nav>
  </aside>
</template>

<script setup lang="ts">
import { RouterLink } from 'vue-router';
import { routes } from '../../src/router/Router';

const menuRoutes = routes.filter(r => r.meta); // lọc ra các route có menu
</script>
