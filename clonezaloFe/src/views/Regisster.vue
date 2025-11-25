<script setup lang="ts">
import { ref } from "vue";
import { register } from "../service/authService";
import { useRouter } from "vue-router";

const router = useRouter();
const name = ref("");
const phone = ref("");
const password = ref("");
const showPassword=ref(false);

const togglePassword = () => {
  showPassword.value = !showPassword.value;
};
const handleRegister = async () => {
  try {
    await register({
      name: name.value,
      phone: phone.value,
      password: password.value,
    });

    alert("Đăng ký thành công! Vui lòng đăng nhập.");
    router.push({ name: "login" });
  } catch (err: any) {
  console.error(err);

  // Nếu AxiosError có response từ backend
  if (err.response && err.response.status === 400) {
    alert(err.response.data.data.name||err.response.data.data.phone||err.response.data.data.password); // thông báo lỗi cụ thể từ backend
  }
  else if (err.response && err.response.status === 404) {
    alert(err.response.data.message); // thông báo lỗi cụ thể từ backend
  }
}

};

</script>

<template>
  <div class="max-w-md mx-auto mt-24 p-8 bg-white shadow-2xl rounded-2xl border border-gray-100">
    <h2 class="text-3xl font-bold mb-8 text-center text-gray-800">
      📝 Đăng ký
    </h2>

    <div class="mb-5">
      <label class="block mb-2 text-sm font-semibold text-gray-600">Tên</label>
      <input
        v-model="name"
        placeholder="Nhập tên..."
        class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-blue-400 focus:border-blue-400 outline-none transition"
      />
    </div>

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

  <!-- Bọc input + icon trong div relative -->
  <div class="relative">
    <input
      v-model="password"
      :type="showPassword ? 'text' : 'password'"
      placeholder="••••••••"
      class="w-full px-4 py-3 pr-12 border rounded-xl focus:ring-2 focus:ring-blue-400 focus:border-blue-400 outline-none transition"
    />

    <!-- Icon con mắt -->
    <button
      type="button"
      @click="togglePassword"
      class="absolute right-4 top-1/2 -translate-y-1/2 text-gray-500 hover:text-gray-700"
    >
      <span v-if="showPassword">🙈</span>
      <span v-else>👁️</span>
    </button>
  </div>
</div>

    

    <button
      @click="handleRegister"
      class="w-full py-3 rounded-xl bg-gradient-to-r from-blue-500 to-indigo-600 text-white font-semibold hover:scale-[1.02] hover:shadow-lg transition transform duration-200"
    >
      Đăng ký
    </button>

    <p class="text-center text-sm text-gray-500 mt-6">
      Đã có tài khoản?
      <router-link to="/login" class="text-blue-500 hover:underline font-medium">
        Đăng nhập
      </router-link>
    </p>
  </div>
</template>
