<template>
  <aside class="w-16 bg-blue-600 p-2 flex flex-col h-screen">
    <!-- Menu -->
    <nav class="flex-1 flex flex-col items-center space-y-3 mt-4">
      <button
        v-for="item in menu"
        :key="item.id"
        class="flex justify-center items-center w-10 h-10 rounded-full hover:bg-blue-500 transition"
        :title="item.title"
        @click="selectMenu(item.view)"
      >
        <!-- Nếu có icon hiển thị icon -->
        <span v-if="item.icon" class="text-white text-lg">{{ item.icon }}</span>
        <!-- Nếu không có icon hiển thị chữ đầu -->
        <span v-else class="text-white text-lg">{{ item.title.charAt(0) }}</span>
      </button>
    </nav>

    <!-- User dropdown -->
    <div class="relative mt-auto mb-2 flex justify-center">
      <div
        @click="toggleDropdown"
        class="w-10 h-10 rounded-full bg-white flex items-center justify-center text-blue-600 font-bold cursor-pointer hover:bg-blue-100 transition"
      >
        {{ userInitial }}
      </div>

      <!-- Dropdown -->
      <div
        v-if="showDropdown"
        class="absolute bottom-full left-1/2 transform -translate-x-1/2 mb-2 w-40 bg-white rounded-md shadow-lg overflow-hidden z-10"
      >
        <div class="py-1">
          <button
            @click="handleLogout"
            class="w-full text-left px-4 py-2 text-sm hover:bg-gray-100 text-gray-700 flex items-center"
          >
            <span class="mr-2">🚪</span> Đăng xuất
          </button>
        </div>
      </div>
    </div>
  </aside>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'

// Định nghĩa kiểu menu
type ViewType = 'friends' | 'contacts' | 'register'

const menu: { id: number; title: string; icon: string; view: ViewType }[] = [
  { id: 1, title: 'Tin nhắn', icon: '💬', view: 'friends' },
  { id: 2, title: 'Danh bạ', icon: '📇', view: 'contacts' },
   { id: 3, title: 'đăng ký', icon: '📇', view: 'register' },
]

// Router
const router = useRouter()
const showDropdown = ref(false)

// Emit sự kiện khi chọn menu
const emit = defineEmits<{ (e: 'changeView', view: ViewType): void }>()

// User info
const userData = JSON.parse(localStorage.getItem('user') || '{}')
const userName = computed(() => userData.name || 'User')
const userInitial = computed(() => userName.value.charAt(0).toUpperCase())

// Toggle dropdown
const toggleDropdown = () => {
  showDropdown.value = !showDropdown.value
}

// Logout
const handleLogout = () => {
  localStorage.removeItem('token')
  localStorage.removeItem('user')
  router.push('auth/login')
  showDropdown.value = false
}

// Khi click menu
function selectMenu(view: ViewType) {
  emit('changeView', view)
}
</script>

<style scoped>
/* Hiệu ứng khi active route */
.router-link-active {
  background-color: rgb(59 130 246 / 0.5);
}
</style>
