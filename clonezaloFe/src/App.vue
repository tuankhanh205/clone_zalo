<!-- src/App.vue -->
<template>
  <div class="flex h-screen">
    <Sidebar />
    <main class="flex-1 bg-gray-50 p-4 overflow-y-auto">
      <router-view />
    </main>
  </div>
</template>

<script setup lang="ts">
import Sidebar from './components/Sidebar.vue';
</script>
