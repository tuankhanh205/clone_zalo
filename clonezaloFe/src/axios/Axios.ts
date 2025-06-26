// src/services/authService.ts
import axios from 'axios';

const API_URL = 'http://localhost:8080/api/auth';

export const AuthService = {
  async login(phone: string, password: string) {
    try {
      const response = await axios.post(
        `${API_URL}/login`,
        {
          phone: phone,
          password: password // Đảm bảo trường này viết thường
        },
        {
          headers: {
            'Content-Type': 'application/json' // Thêm header này
          },
          withCredentials: true // Nếu dùng session/cookie
        }
      );
      return response.data;
    } catch (error) {
      if (axios.isAxiosError(error)) {
        console.error('Chi tiết lỗi:', error.response?.data);
        throw new Error(error.response?.data?.message || 'Đăng nhập thất bại');
      }
      throw error;
    }
  }
};