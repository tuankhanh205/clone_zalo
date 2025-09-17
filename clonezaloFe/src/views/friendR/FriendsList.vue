<template>
  <div class="p-2 bg-gray-100 h-full">
    <h2 class="text-lg font-semibold mb-2">Danh sách bạn bè</h2>
    <ul class="w-full h-full bg-white rounded shadow overflow-hidden">
      <li v-for="invite in friendRes" :key="invite.id" class="border-b last:border-b-0">
        <div class="flex items-center gap-3 p-3 hover:bg-gray-200 cursor-pointer">
          <img 
            :src="invite.image" 
            alt="avatar" 
            class="w-8 h-8 rounded-full object-cover"
          /> 
          <span class="font-medium text-gray-800">{{ invite.name }}</span>
        </div>
      </li>
    </ul>
  </div>
</template>

<script setup lang="ts">
import { ref,onMounted } from 'vue';
import { getFriends } from '../../service/friendService';
import type { FriendResponse } from '../../types/friend';
import type { FriendRRequest } from '../../types/friendR';
import FriendRService from '../../service/FriendRService';
const friendRes=ref<FriendResponse[]>([])


const loadlistFriend=async()=>{
const rp=await getFriends()
console.log('danh sach bb',rp)
if(Array.isArray(rp)){
    friendRes.value=rp
}
else{
     friendRes.value = [rp]
}
}
onMounted(() => {
  loadlistFriend();
});
</script>