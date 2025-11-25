(window as any).global = window 
import { createApp } from 'vue';
import './style.css';
import { createPinia } from 'pinia' 
import App from './App.vue';
import router from './router/Router';
const pinia = createPinia()
const app = createApp(App);
app.use(pinia)
app.use(router); // ✅ đăng ký router vào Vue app
app.mount('#app');
