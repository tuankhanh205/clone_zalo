<template>
  <div class="flex h-screen">
    <!-- Sidebar trái -->
    <aside class="w-20 bg-blue-500 border-r flex flex-col justify-between items-center h-full relative">
      <!-- Menu icons trên cùng -->
      <div class="flex flex-col items-center mt-4 space-y-4">
        <button @click="switchTab('lienlac')">💬</button>
        <button @click="switchTab('friendList')" >👥</button>
        <button @click="switchTab('news')">📰</button>
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
            <RouterLink 
            to="/dangxuat" 
            class="block px-4 py-2 hover:bg-gray-100 text-gray-700"
            @click="closeDropdown"
          >
           hồ sơ tài khoản
          </RouterLink>
        </div>
      </div>
    </aside>

    <!-- Cột phải thay đổi theo layout -->
    <div class="flex-1">
      <ContactLayout v-if="currentTab === 'lienlac'" />
      <FriendList v-if="currentTab === 'friendList'" />
      <div v-if="currentTab === 'news'">Trang tin tức</div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import ContactLayout from "./ContactLayout.vue";
import FriendList from "./FriendList.vue";
import { RouterLink } from "vue-router";

// Lấy tab từ localStorage nếu có, mặc định là 'lienlac'
const currentTab = ref(localStorage.getItem("currentTab") || "lienlac");
const showDropdown = ref(false);

// Hàm chuyển tab, lưu vào localStorage
function switchTab(tab: string) {
  currentTab.value = tab;
  localStorage.setItem("currentTab", tab);
}

// Tạo class cho nút tab đang active


// Dropdown avatar
function toggleDropdown() {
  showDropdown.value = !showDropdown.value;
}

function closeDropdown() {
  showDropdown.value = false;
}
</script>
