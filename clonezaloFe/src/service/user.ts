import axios from "axios";
import type { UserResponse } from "../types/user";

const apiClient = axios.create({
  baseURL: "http://localhost:8080/api/user",
});

// Interceptor tự động thêm token vào mọi request
apiClient.interceptors.request.use(config => {
  const token = localStorage.getItem("token");
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

const userService = {
  // Trả về đúng kiểu UserResponse
  getUser: async (phone: string): Promise<UserResponse[]> => {
    const response = await apiClient.get<UserResponse[]>(`/fin-phone/${phone}`);
    return response.data;
  },
};

export default userService;
