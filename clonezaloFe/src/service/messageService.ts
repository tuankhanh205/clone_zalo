import axios from "axios";
import type { MessagesRequest, MessagesResponse } from "../types/messages";

// Tạo instance axios với interceptor tự động gắn token
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

export const messageService = {
  /**
   * Gửi tin nhắn (text hoặc file)
   */
 async sendMessage(
  receiverId: number,
  content: string,
  file: File | null = null
): Promise<MessagesResponse> {
  const formData = new FormData();
  // JSON string theo backend mong đợi
formData.append("request", JSON.stringify({ message: content }));

  if (file) formData.append("file", file);

  // gửi receiverId qua query param vì backend dùng @RequestParam
  const response = await apiClient.post(
    `/messages?receiverId=${receiverId}`,
    formData,
    { headers: { "Content-Type": "multipart/form-data" } }
  );

  return response.data;
},


  /**
   * Lấy danh sách tin nhắn
   */
  async listMessages(receiverId: number): Promise<MessagesResponse[]> {
    const response = await apiClient.get(`/list/messages/${receiverId}`);
    return response.data;
  }
};