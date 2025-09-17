<template>
 <div class="flex h-screen">
    <!-- Sidebar trái -->
    <div class="w-70 bg-white ">
        <button class="w-full h-10 hover:bg-gray-200 mt-5 transition duration-500 ease-in-out" @click="theodoi('friendsList')">danh sách bạn bè</button>
        <button class="w-full h-10  hover:bg-gray-200 transition duration-500 ease-in-out" @click="theodoi('friendInvitation')">danh sách lời mời</button>
    </div>

    <!-- Cột phải scroll riêng -->
    <div class="w-full h-screen overflow-auto bg-gray-200">
          <FriendsList v-if="chuyenTrang==='friendsList'" ></FriendsList>
          <friendInvitation v-if="chuyenTrang==='friendInvitation'" ></friendInvitation>
    </div>
 </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import friendInvitation from '../views/friendR/FriendInvitation.vue';
import FriendsList from '../views/friendR/FriendsList.vue';

const chuyenTrang=ref('friendsList')
const theodoi=(tentrang:string)=>{
  chuyenTrang.value=tentrang
}
</script>
