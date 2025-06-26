<template>
  <div class="flex h-screen overflow-hidden bg-gray-50">
    <!-- Sidebar cố định -->
    <Sidebar class="w-64 shrink-0 border-r border-gray-200" />

    <!-- Nội dung cuộn -->
    <main class="flex-1 overflow-y-auto p-6">
      <RouterView />
    </main>
  </div>
</template>

<script setup lang="ts">
import Sidebar from '../components/Sidebar.vue';
</script>
