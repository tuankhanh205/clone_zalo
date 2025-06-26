// src/services/authService.ts
import axios from 'axios';

const API_URL = 'http://localhost:8080/api/auth';

export const AuthService = {
  async login(credentials: { phone: string; password: string }) {
    //                          ^ Đổi thành object
    try {
      const response = await axios.post(
        `${API_URL}/login`,
        credentials, // Truyền nguyên object
        {
          headers: { 'Content-Type': 'application/json' }
        }
      );
      return response.data;
    } catch (error) {
      if (axios.isAxiosError(error)) {
        // Xử lý lỗi từ server (nếu có message)
        throw new Error(error.response?.data?.message || error.message);
      }
      throw error;
    }
  }
};