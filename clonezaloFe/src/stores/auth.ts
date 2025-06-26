// src/stores/auth.ts
import { defineStore } from 'pinia';
import { AuthService } from '../service/authService'; 
import type { LoginResponse,LoginCredentials } from '../types/auth'; 

interface AuthState {
  user: {
    name: string;
    phone: string;
    role: string;
  } | null;
  accessToken: string | null;
  refreshToken: string | null;
}

export const useAuthStore = defineStore('auth', {
  state: (): AuthState => ({
    user: null,
    accessToken: null,
    refreshToken: null,
  }),
  actions: {
    async login(credentials: LoginCredentials) {
      try {
        const response = await AuthService.login(credentials);
        this.user = response.data.user;
        this.accessToken = response.data.accessToken;
        this.refreshToken = response.data.refreshToken;
        
        localStorage.setItem('accessToken', this.accessToken);
        return response;
      } catch (error) {
        if (error instanceof Error) {
          throw error;
        }
        throw new Error('Unknown error occurred');
      }
    },
  },
  getters: {
    isAuthenticated: (state): boolean => !!state.accessToken,
  },
});