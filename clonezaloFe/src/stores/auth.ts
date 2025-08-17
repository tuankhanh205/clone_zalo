// // stores/auth.ts
// export const useAuthStore = defineStore('auth', {
//   state: () => ({
//     token: localStorage.getItem('token')
//   }),
//   actions: {
//     login(token: string) {
//       this.token = token
//       localStorage.setItem('token', token)
//     }
//   }
// })

// // Trong component
// const auth = useAuthStore()
// watch(() => auth.token, (newVal) => {
//   // Tự động cập nhật khi token thay đổi
// })