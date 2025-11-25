import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import tailwindcss from '@tailwindcss/vite'

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue(), tailwindcss()],
  define: {
    global: 'window', // thêm dòng này để polyfill `global`
  },
  optimizeDeps: {
    include: ['sockjs-client'], // đảm bảo Vite xử lý SockJS đúng
  },
})
