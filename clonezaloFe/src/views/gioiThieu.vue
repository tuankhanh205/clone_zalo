<template>
  <div class="intro-container flex flex-col items-center p-6 bg-gradient-to-r from-blue-100 to-purple-100 rounded-xl shadow-lg">
    <!-- Ảnh đại diện -->
    <img 
      src="/src//assets//image/daidien.png" 
      alt="Avatar Trương Quang Tuấn Khanh" 
      class="w-32 h-32 rounded-full border-4 border-white shadow-md mb-4 object-cover"
    />

    <!-- Tên và tuổi -->
    <h1 class="text-2xl font-bold text-gray-800 mb-2 text-center">
      Trương Quang Tuấn Khanh
    </h1>
    <p class="text-gray-600 mb-4 text-center">
      20 tuổi, đến từ Hà Tĩnh
    </p>

    <!-- Giới thiệu về web -->
    <div class="text-center max-w-xl">
      <p class="mb-2 text-gray-700">
        Chào mừng bạn đến với trang web được phát triển dựa trên trải nghiệm của Zalo.
      </p>
      <p class="mb-2 text-gray-700">
        Đây là nền tảng giúp bạn kết nối, trò chuyện và chia sẻ với bạn bè một cách nhanh chóng và tiện lợi.
      </p>
      <p class="mb-4 text-gray-700">
        Web được phát triển bởi Trương Quang Tuấn Khanh với niềm đam mê công nghệ và giao tiếp trực tuyến.
      </p>
    </div>

    <!-- Nút khám phá -->
    <button class="mt-4 px-6 py-2 bg-blue-500 text-white rounded-full shadow hover:bg-blue-600 transition">
      Khám phá ngay
    </button>
  </div>
</template>

<style scoped>
.intro-container {
  min-height: 400px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
</style>
