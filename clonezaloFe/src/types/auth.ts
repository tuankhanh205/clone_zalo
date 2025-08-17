// src/types/auth.ts
export interface LoginResponse {
  status: number;
  message: string;
  data: {
    accessToken: string;
    refreshToken: string;
    user: {
      name: string;
      phone: string;
      role: string;
    };
  };
}

export interface LoginRequest {
  phone: string;
  passWord: string;
}

// regiter
export interface RegisterRequest {
  phone: string;
  password: string;
  name: string;
}
export interface AuthResponse {
  accessToken: string;
  refreshToken: string;
  user: UserInfo;
}

export interface UserInfo {
  userId: number;
  name: string;
  phone: string;
  role: string;
}
