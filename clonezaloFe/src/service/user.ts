import axios from "axios";
import type { UserResponse } from "../types/user";

const apiClient = axios.create({
  baseURL: "http://localhost:8080/api/user",
});

// Interceptor tự động thêm token vào mọi request
apiClient.interceptors.request.use(config => {
  const token = sessionStorage.getItem("token");
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
  finbyid:async(id:number):Promise<UserResponse>=>{
    const data= await apiClient.get<UserResponse>(`/${id}`)
    return data.data
  }
};

export default userService;
