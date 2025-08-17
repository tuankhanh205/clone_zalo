import axios from "axios";
import type { LoginRequest, AuthResponse, RegisterRequest } from "../types/auth";

const API_URL = "http://localhost:8080/api/auth";

// Login
export const login = async (loginRequest: LoginRequest): Promise<AuthResponse> => {
  try {
    const response = await axios.post(`${API_URL}/login`, loginRequest);

    // Lưu accessToken vào localStorage
    localStorage.setItem("token", response.data.data.accessToken);

    return response.data.data; // trả về AuthResponse cho component
  } catch (error) {
    console.error("Đăng nhập thất bại:", error);
    throw error;
  }
};

// Register
export const register = async (registerRequest: RegisterRequest) => {
  try {
    const response = await axios.post(`${API_URL}/register`, registerRequest);
    return response.data.data; // chỉ trả user info
  } catch (error) {
    console.error("Đăng ký thất bại:", error);
    throw error;
  }
};

