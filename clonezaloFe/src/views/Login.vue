<script setup lang="ts">
import { ref, onMounted } from "vue";
import { login } from "../service/authService";
import { useRouter } from "vue-router";

const router = useRouter();
const phone = ref("");
const passWord = ref("");

const handleLogin = async () => {
  try {
    const res = await login({
      phone: phone.value,
      passWord: passWord.value,
    });

    // Lưu token trước khi chuyển trang
  sessionStorage.setItem("token", res.accessToken);
     sessionStorage.setItem("userId", res.user.userId.toString());
 console.log(res.accessToken)
    // Chuyển hướng sang Contacts
    await router.push({ name: 'gioithieu' });
  } catch (err) {
    alert("Sai thông tin đăng nhập!");
  }
};


// Kiểm tra đăng nhập khi trang load
onMounted(() => {
  if (sessionStorage.getItem("token")) {
    router.replace({ name: 'Contacts' }); // Nếu đã login thì chuyển hướng luôn
  }
});
</script>

<template>
  <div class="max-w-md mx-auto mt-24 p-8 bg-white shadow-2xl rounded-2xl border border-gray-100">
    <h2 class="text-3xl font-bold mb-8 text-center text-gray-800">
      🔐 Đăng nhập
    </h2>

    <div class="mb-5">
      <label class="block mb-2 text-sm font-semibold text-gray-600">Số điện thoại</label>
      <input
        v-model="phone"
        placeholder="Nhập số điện thoại..."
        class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-blue-400 focus:border-blue-400 outline-none transition"
      />
    </div>

    <div class="mb-6">
      <label class="block mb-2 text-sm font-semibold text-gray-600">Mật khẩu</label>
      <input
        v-model="passWord"
        type="password"
        placeholder="••••••••"
        class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-blue-400 focus:border-blue-400 outline-none transition"
      />
    </div>

    <button
      @click="handleLogin"
      class="w-full py-3 rounded-xl bg-gradient-to-r from-blue-500 to-indigo-600 text-white font-semibold hover:scale-[1.02] hover:shadow-lg transition transform duration-200"
    >
      Đăng nhập
    </button>

    <p class="text-center text-sm text-gray-500 mt-6">
  Chưa có tài khoản?
  <RouterLink to="/register" class="text-blue-500 hover:underline font-medium">
    Đăng ký ngay
  </RouterLink>
</p>

  </div>
</template>