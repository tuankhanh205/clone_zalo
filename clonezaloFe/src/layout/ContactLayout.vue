<template>
  <div class="flex h-screen">
    <!-- Cột 1: Danh sách chat -->
    <div class="w-72 border-r p-4 bg-gray-100 h-full overflow-y-auto">
      <!-- Lắng nghe sự kiện selectFriend -->
      <FriendList @selectFriend="handleSelectFriend" />
    </div>

    <!-- Cột 2: Khung chat -->
    <div class="flex-1 border-r p-4 h-full overflow-y-auto">
   <MessageForm
  v-if="selectedFriend"
  :receiverId="selectedFriend.id"
  :userReceiverName="selectedFriend.name"
  :receiverAvatar="selectedFriend.image"
/>

    </div>

    <!-- Cột 3: Thông tin hội thoại -->
    <div class="w-80 p-4 bg-gray-50 h-full overflow-y-auto">
      <div v-if="selectedFriend">
        <h3 class="text-lg font-bold mb-2">Thông tin bạn bè</h3>
        <p><b>Tên:</b> {{ selectedFriend.name }}</p>
        <p><b>ID:</b> {{ selectedFriend.id }}</p>
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
