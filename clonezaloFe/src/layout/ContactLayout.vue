<template>
  <div class="flex h-screen">
    <!-- Cột 1: Danh sách chat -->
    <div class="w-72 border-r p-4 bg-gray-100 h-full overflow-y-auto">
      <!-- Lắng nghe sự kiện selectFriend -->
      <FriendList @selectFriend="handleSelectFriend" />
    </div>

    <!-- Cột 2: Khung chat -->
    <div class="flex-1 border-r p-4 h-full overflow-y-auto">
      <MessageForm v-if="selectedFriend" :receiverId="selectedFriend.id" :userReceiverName="selectedFriend.name"
        :receiverAvatar="selectedFriend.image" />

    </div>

    <!-- Cột 3: Thông tin hội thoại -->
    <div class="w-80 p-4 bg-gray-50 h-full overflow-y-auto">
      <div v-if="selectedFriend">
        <h3 class="text-lg font-bold ">Thông tin bạn bè</h3>
        <hr class="mb-4 mt-5">
        <div class="text-center">
          <img :src="selectedFriend.image" class="w-20 h-20 object-cover rounded-full inline-block">
        </div>

        <div class="mt-2">
          <p class="text-center">{{ selectedFriend.name }}</p>
        </div>
        <div class="w-ful h-3 mt-7 bg-gray-300"></div>

        <div>
          <button class="px-3 py-1 w-full text-left hover:bg-gray-300 transition duration-300 ease-in-out">Danh sách
            nhắc hẹn</button>
          <button class="px-3 py-1 w-full text-left  hover:bg-gray-300 transition duration-300 ease-in-out">0 nhóm
            chung</button>

        </div>
        <div class="w-ful h-3 bg-gray-300"></div>
        <!-- ảnh viceo -->
        <div>
          <span class="pl-2 font-bold text-sm">Ảnh/Video</span>
          <p class="w-full h-50 text-center pt-20"> chưa có</p>
        </div>
          <div class="w-ful h-3 bg-gray-300"></div>
          <!-- file -->
          <div>
            <span class="pl-2 font-bold text-sm">File</span>
            <p class="w-full  text-center text-sm">chưa có file được chia sẻ trong hội thoại này</p>
          </div>
                  <div class="w-ful h-3 bg-gray-300"></div>
           <!-- link -->
          <div>
            <span class="pl-2 font-bold text-sm">Link</span>
            <p class="w-full  text-center text-sm">chưa có link được chia sẻ trong hội thoại này</p>
          </div>
      </div>
      <div v-else class="text-gray-400">Chưa chọn bạn bè</div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import FriendList from "../views/friend/FriendList.vue";
import Contacts from "../views/Contacts.vue";
import MessageForm from "../views/message/MessageForm.vue";

const selectedFriend = ref<any | null>(null);

function handleSelectFriend(friend: any) {
  selectedFriend.value = friend;
  console.log("Đã chọn:", friend);
}
</script>
