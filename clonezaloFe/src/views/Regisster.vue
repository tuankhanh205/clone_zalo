<script setup lang="ts">
import { ref } from "vue";
import { register } from "../service/authService";
import { useRouter } from "vue-router";

const router = useRouter();
const name = ref("");
const phone = ref("");
const password = ref("");

const handleRegister = async () => {
  try {
    await register({
      name: name.value,
      phone: phone.value,
      password: password.value,
    });

    alert("Đăng ký thành công! Vui lòng đăng nhập.");
    router.push({ name: "Login" });
  } catch (err) {
    console.error(err);
    alert("Đăng ký thất bại! Vui lòng thử lại.");
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
      <input
        v-model="password"
        type="password"
        placeholder="••••••••"
        class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-blue-400 focus:border-blue-400 outline-none transition"
      />
    </div>

    <button
      @click="handleRegister"
      class="w-full py-3 rounded-xl bg-gradient-to-r from-blue-500 to-indigo-600 text-white font-semibold hover:scale-[1.02] hover:shadow-lg transition transform duration-200"
    >
      Đăng ký
    </button>

    <p class="text-center text-sm text-gray-500 mt-6">
      Đã có tài khoản?
      <router-link to="/auth/login" class="text-blue-500 hover:underline font-medium">
        Đăng nhập
      </router-link>
    </p>
  </div>
</template>
