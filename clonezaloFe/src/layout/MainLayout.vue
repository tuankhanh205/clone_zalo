<template>
  <div class="flex h-screen">
    <!-- Sidebar trái -->
    <aside class="w-20 bg-blue-500 border-r flex flex-col justify-between items-center h-full relative">
      <!-- Menu icons trên cùng -->
      <div class="flex flex-col items-center mt-4 space-y-4">
        <button @click="switchTab('lienlac')">💬</button>
        <button @click="switchTab('friendList')">👥</button>
        <button @click="switchTab('news')">📰</button>
      </div>

      <!-- Avatar + dropdown ở dưới cùng -->
      <div class="relative mb-4">
        <button type="button" @click="toggleDropdown"
          class="w-8 h-8 rounded-full bg-white flex items-center justify-center text-xl hover:bg-gray-200">
          👤
        </button>

        <div v-if="showDropdown" class="absolute left-full bottom-0 bg-white border rounded-lg shadow-lg w-32 z-50"
          @click.stop>
          <RouterLink to="/dangxuat" class="block px-4 py-2 hover:bg-gray-100 text-gray-700"@click.native.prevent="logout" @click="closeDropdown">
            Đăng xuất
          </RouterLink>
          <RouterLink to="#" class="block px-4 py-2 hover:bg-gray-100 text-gray-700" @click.prevent="openProfile">
            Hồ sơ tài khoản
          </RouterLink>

        </div>
      </div>
    </aside>

    <!-- Cột phải thay đổi theo layout -->
    <div class="flex-1">
      <ContactLayout v-if="currentTab === 'lienlac'" />
      <FriendList v-if="currentTab === 'friendList'" />
      <div v-if="currentTab === 'news'">Trang tin tứck</div>
    </div>
  </div>
  <div v-if="showProfile" class="fixed inset-0 flex justify-center items-center z-50">
    <!-- Backdrop mờ, click đóng -->
     
    <div class="absolute inset-0 bg-black/40" @click="closeProfile"></div>

    <!-- Modal nổi lên -->
    <div class="relative bg-white  pb-6 rounded-xl w-96 shadow-lg z-50">
       
      <div class="pb-4 pt-4 ml-4"><span class="font-bold">thông tin tài khoản</span>
      <!-- Header -->
      
       <button 
    @click="closeProfile" 
    class="absolute top-2 right-2 text-gray-500 hover:text-gray-700 text-xl font-bold"
  >
    ✕
  </button>
  </div>
      <div class=" items-center gap-4 ">

        <div class="w-full h-30 bg-amber-200">
          <div class="w-full h-20 rounded-full flex pt-22 pl-5">


            <template v-if="userResponse?.image">
              <img :src="userResponse.image||'../assets/image/ảnh đại diện trắng.png'" alt="avatar" class="w-20 h-20 object-cover rounded-full" />
            </template>
            <template v-else>
              {{ userResponse?.userName?.charAt(0).toUpperCase() }}
            </template>
            <h3 class="font-bold pt-10">{{ userResponse?.userName }}</h3>
          </div>

        </div>


      </div>

      <div class="w-full h-2 bg-gray-200 mt-17" ></div>
      <!-- Thông tin -->
      <div class="mt-4 ml-4">
        <span class="font-bold">Thông tin cá nhân</span>
        <div class="pt-3">
        <p>Giới tính <span class="ml-10">{{ userResponse?.sex }}</span></p>
        <p> Ngày sinh: <span class="ml-7">{{ userResponse?.dateOfBirth }}</span></p>
        <p>Điện thoại: <span class="ml-7">{{ userResponse?.phone || 'Chưa có' }}</span></p>
        </div>
      </div>

      <!-- Nút cập nhật -->
<div class="text-center mt-6">
  <button 
    class="px-4 py-2 bg-gray-400 text-white rounded-lg hover:bg-gray-500"
    @click="openUpdateForm"
  >
    Cập nhật
  </button>
</div>

    </div>
  </div>
<!-- dialog cap nhat  -->
<!-- Form cập nhật -->
<div v-if="showUpdateForm && userResponse" class="fixed inset-0 flex justify-center items-center z-[105]">
  <!-- Backdrop -->
  <div class="absolute inset-0 bg-black/30" @click="closeUpdateForm"></div>

  <!-- Form nổi -->
  <div class="relative bg-white p-6 rounded-xl w-96 shadow-lg z-[110]">
    <button 
      @click="closeUpdateForm"
      class="absolute top-2 right-2 text-gray-500 hover:text-gray-700 text-xl font-bold"
    >
      ✕
    </button>

    <h3 class="text-xl font-bold mb-4">Cập nhật thông tin</h3>

    <form @submit.prevent="" class="space-y-4">
      <div>
        <label class="block font-semibold">Tên</label>
        <input v-model="userResponse.userName" class="w-full border rounded px-2 py-1" />
      </div>
      <div>
        <label class="block font-semibold">Giới tính</label>
        <select v-model="userResponse.sex" class="w-full border rounded px-2 py-1">
          <option value="Nam">Nam</option>
          <option value="Nữ">Nữ</option>
        </select>
      </div>
      <div>
        <label class="block font-semibold">Ngày sinh</label>
        <input type="date" v-model="userResponse.dateOfBirth" class="w-full border rounded px-2 py-1" />
      </div>

      <div class="text-center">
        <button type="submit" class="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600">
          Lưu thay đổi
        </button>
      </div>
    </form>
  </div>
</div>

</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import ContactLayout from "./ContactLayout.vue";
import FriendList from "./FriendList.vue";
import type { UserResponse } from "../types/user";
import { RouterLink } from "vue-router";
import userService from "../service/user";
const showProfile = ref(false);
const showUpdateForm = ref(false)

function logout() {
  // 1. Xóa token và thông tin user
  sessionStorage.removeItem('token');
  sessionStorage.removeItem('userId');
  sessionStorage.removeItem('currentTab');

  // 2. Chuyển về trang login
  window.location.href = '/login'; // hoặc router.replace({ name: 'login' })
}

function openUpdateForm() {
  showUpdateForm.value = true
}

function closeUpdateForm() {
  showUpdateForm.value = false
}

function openProfile() {
  showProfile.value = true;
  closeDropdown(); // đóng dropdown khi mở modal
}

function closeProfile() {
  showProfile.value = false;
}

const userResponse = ref<UserResponse | null>(null)
const userIdStr = sessionStorage.getItem("userId")
const userId = userIdStr ? Number(userIdStr) : undefined;
const getUser = async () => {
  try {
    if (userId !== undefined) {
      const data = await userService.finbyid(userId);
      userResponse.value = data
      console.log("dl user", data)

    }
  }
  catch (err: any) {
    console.log(err)
  }

}
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
onMounted(() => {
  getUser();
});
</script>
