<script setup lang="ts">
import Sidebar from '../components/Sidebar.vue'
import FriendList from '../views/friend/FriendList.vue'
import Contacts from '../views/Contacts.vue'
// import ChatBox from '../components/ChatBox.vue'
import { ref } from 'vue'

import GioiThieu from '../views/gioiThieu.vue'
import Regisster from '../views/Regisster.vue'

// Cột 2
type ViewType = 'friends' | 'contacts' | 'register'

// Cột 2
const currentView = ref<ViewType>('friends')
const selectedFriend = ref<any>(null)

function handleSelectFriend(friend: any) {
  selectedFriend.value = friend
}
</script>

<template>
  <div class="flex h-screen">
    <!-- Cột 1: Sidebar -->
    <Sidebar @changeView="currentView = $event" class="w-20 border-r border-gray-200" />

    <!-- Cột 2 -->
    <div class="w-80 border-r border-gray-200 p-2">
      
      <FriendList v-if="currentView === 'friends'" @selectFriend="handleSelectFriend" />
      <Contacts v-else-if="currentView === 'contacts'" />
       <Regisster v-else-if="currentView === 'register'" />
      <div v-else>Chưa chọn view</div>
    </div>

   <!-- Cột 3: Chat box -->
<div class="flex-1 p-6 overflow-y-auto flex flex-col">
  <Messageform 
    v-if="currentView === 'friends' && selectedFriend" 
    :receiver-id="selectedFriend.id"
    :user-receiver-name="selectedFriend.name" 
  />
  <div v-else class="text-gray-400"><GioiThieu/></div>
</div>


    <!-- Cột 4: Thông tin hội thoại -->
    <div class="w-60 border-l border-gray-200 p-4">
      <h3 class="font-bold mb-2">Thông tin hội thoại</h3>
      <p v-if="selectedFriend">Chat với: {{ selectedFriend.name }}</p>
      <p v-if="selectedFriend">Chat với: {{ selectedFriend.id }}</p>
      <p v-else>Chưa chọn hội thoại</p>
    </div>
  </div>
</template>
