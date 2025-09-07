<template>
  <div class="flex h-screen">
    <!-- Sidebar trái -->
    <aside class="w-20 bg-blue-500 border-r flex flex-col justify-between items-center h-full relative">
  <!-- Menu icons trên cùng -->
  <div class="flex flex-col items-center mt-4 space-y-4">
    <button @click="currentTab = 'lienlac'" class="p-4 hover:bg-gray-300">💬</button>
    <button @click="currentTab = 'contacts'" class="p-4 hover:bg-gray-300">👥</button>
    <button @click="currentTab = 'news'" class="p-4 hover:bg-gray-300">📰</button>
  </div>

  <!-- Avatar + dropdown ở dưới cùng -->
  <div class="relative mb-4">
    <button 
      type="button"
      @click="toggleDropdown" 
      class="w-8 h-8 rounded-full bg-white flex items-center justify-center text-xl hover:bg-gray-200"
    >
      👤
    </button>

    <div 
      v-if="showDropdown" 
      class="absolute left-full bottom-0 bg-white border rounded-lg shadow-lg w-32 z-50"
      @click.stop
    >
      <RouterLink 
        to="/dangxuat" 
        class="block px-4 py-2 hover:bg-gray-100 text-gray-700"
        @click="closeDropdown"
      >
        Đăng xuất
      </RouterLink>
    </div>
  </div>
</aside>


    <!-- Cột phải thay đổi theo layout -->
    <div class="flex-1">
      <ContactLayout v-if="currentTab === 'lienlac'" />
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import ContactLayout from "./ContactLayout.vue";
import { RouterLink } from "vue-router";

const currentTab = ref("lienlac"); 
const showDropdown = ref(false);

function toggleDropdown() {
  showDropdown.value = !showDropdown.value;
}

function closeDropdown() {
  showDropdown.value = false;
}
</script>
