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

export interface LoginCredentials {
  phone: string;
  password: string;
}