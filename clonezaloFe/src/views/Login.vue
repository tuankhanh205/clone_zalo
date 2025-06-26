<script setup lang="ts">
import { ref } from 'vue';
import { AuthService } from '../service/authService'; // Sửa đường dẫn

const phone = ref('0982929517');
const password = ref('khanhson');
const error = ref('');
const isLoading = ref(false);

const handleLogin = async () => {
  try {
    isLoading.value = true;
    error.value = '';
    
    const response = await AuthService.login(
      phone.value, 
      password.value
    );
    
    console.log('Đăng nhập thành công:', response);
    // Xử lý sau khi login thành công (redirect, lưu token...)
    
  } catch (err: any) {
    error.value = err.response?.data?.message || 
                 err.message || 
                 'Đăng nhập thất bại. Vui lòng thử lại.';
    
    console.error('Chi tiết lỗi:', {
      status: err.response?.status,
      data: err.response?.data,
      headers: err.response?.headers
    });
    
  } finally {
    isLoading.value = false;
  }
};
</script>

<template>
  <form @submit.prevent="handleLogin">
    <input v-model="phone" placeholder="Số điện thoại">
    <input v-model="password" type="password" placeholder="Mật khẩu">
    
    <button type="submit" :disabled="isLoading">
      {{ isLoading ? 'Đang xử lý...' : 'Đăng nhập' }}
    </button>
    
    <p v-if="error" class="error">{{ error }}</p>
  </form>
</template>

<style scoped>
.error { color: red; }
</style>