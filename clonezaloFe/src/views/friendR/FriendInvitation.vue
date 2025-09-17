<template>
  <div class="p-2"> 
    <div class="w-full h-15 bg-white p-2 rounded shadow mb-2">
      <h3>Danh sách lời mời kết bạn</h3>
    </div>
   
    <ul class="space-y-4">
      <li v-for="invite in friendRes" :key="invite.sdt">
        <div class="w-60 bg-white p-4 rounded shadow ">
          <div class="flex justify-between items-center mb-2">
            <span>
              <img 
                :src="invite.image" 
                alt="avatar" 
                class="w-8 h-8 rounded-full object-cover"
              /> 
              {{ invite.senderName }}
            </span>
          </div>
          <span class="text-xs">{{ invite.sentAt }} - từ số điện thoại</span>
          <div class="w-full h-20 bg-gray-200 mt-2 mb-2 text-xs p-2">
            Xin chào! Kết bạn với mình nhé!
          </div>
          <div class="flex justify-end gap-2">
            <button class="px-3 py-1 bg-red-500 text-white rounded hover:bg-red-600" @click="confirmInvitation(invite.sdt,'DECLINED')">Từ chối</button>
            <button class="px-3 py-1 bg-green-500 text-white rounded hover:bg-green-600" @click="confirmInvitation(invite.sdt,'ACCEPTED')">Đồng ý</button>
          </div>
        </div>
      </li>
    </ul>

    <div v-if="friendRes.length === 0">Không có lời mời nào</div>
  </div>
</template>


<script setup lang="ts">
import { ref, onMounted } from 'vue';
import FriendRService from '../../service/FriendRService';
import type { FriendRRequest, FriendRResponse } from '../../types/friendR';

const friendRes = ref<FriendRResponse[]>([]);

const statusRef=ref('')

const confirmInvitation=async(sdt:string,status:string)=>{
  try{
  const response=await FriendRService.confirmInvitation({sdt,status}as FriendRRequest)
  console.log(response)
  }
  catch(err:any){
    console.log(err)
   alert("ko thuc hien duoc")
  }
  loadFriendInvitation()

}
const loadFriendInvitation = async () => {
  try {
    const response = await FriendRService.friendInvitation();
    // Nếu response là mảng
    if (Array.isArray(response)) {
      friendRes.value = response;
    } else {
      // Nếu chỉ 1 object
      friendRes.value = [response];
    }
    console.log('Danh sách lời mời:', friendRes.value);
     console.log('Processed friendRes:', friendRes.value); 
  } catch (err) {
    console.error('Lỗi tải lời mời:', err);
  }
};

onMounted(() => {
  loadFriendInvitation();
});
</script>
