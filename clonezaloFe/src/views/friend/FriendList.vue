<template>
  <div class="flex flex-col h-full relative">
    <!-- Header / search -->
    <div class="flex p-4 h-20 border-b border-gray-200 flex-shrink-0 items-center">
      <input 
        type="text" 
        placeholder="Tìm kiếm bạn bè" 
        class="flex-1 h-10 p-2 border rounded focus:outline-none focus:ring-2 focus:ring-blue-400"
        v-model="search"
      />
      <div>
        <img 
          src="/src/assets/image/add-user_9650069.png" 
          alt="" 
          class="w-6 h-6 ml-3 cursor-pointer"
          @click="showAddFriendDialog = true"
        />
      </div>
    </div>

    <!-- Danh sách bạn bè -->
    <div class="flex-1 overflow-y-auto p-4">
      <h3 class="font-bold mb-2">Danh sách bạn bè</h3>
      <ul>
        <li 
          v-for="friend in filteredFriends" 
          :key="friend.id"
          @click="selectFriend(friend)"
          class="cursor-pointer hover:bg-gray-100 p-2 rounded flex items-center gap-2"
        >
          <div class="w-8 h-8 bg-gray-300 rounded-full flex items-center justify-center text-sm text-white">
            <img 
              :src="friend.image" 
              alt="avatar" 
              class="w-10 h-10 rounded-full object-cover"
            />
          </div>
          <span>{{ friend.name }}</span>
        </li>
      </ul>
      <div v-if="friends.length === 0" class="text-gray-400 mt-2">Chưa có bạn bè</div>
    </div>

    <!-- Dialog thêm bạn -->
    <div v-if="showAddFriendDialog" class="fixed inset-0 flex justify-center items-center z-50">
      <!-- Overlay -->
      <div class="absolute inset-0 bg-black opacity-40" @click="closeDialog"></div>

      <!-- Nội dung dialog -->
      <div class="relative bg-white p-6 rounded-2xl w-96 max-h-[80vh] overflow-y-auto shadow-2xl z-50">
        <h3 class="text-xl font-bold mb-4 text-center">Thêm bạn</h3>

        <input 
          type="text" 
          placeholder="Nhập số điện thoại" 
          class="w-full p-3 border rounded-lg mb-4 focus:outline-none focus:ring-2 focus:ring-blue-400"
          v-model="searchPhone"
        />

        <div class="flex justify-end gap-3 mb-4">
          <button 
            class="px-4 py-2 bg-gray-200 rounded-lg hover:bg-gray-300"
            @click="closeDialog"
          >
            Hủy
          </button>
          <button 
            class="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600"
            @click="timKiem(searchPhone)"
          >
            Tìm
          </button>
        </div>

        <!-- Kết quả tìm kiếm -->
        <div v-if="searchResults.length > 0">
          <h4 class="font-semibold mb-2 text-gray-700">Kết quả tìm kiếm</h4>
          <ul class="space-y-2">
            <li 
              v-for="user in searchResults" 
              :key="user.id"
              class="flex items-center justify-between p-2 bg-gray-50 hover:bg-gray-100 rounded-lg cursor-pointer"
              @click="openUserDialog(user)"
            >
              <div class="flex items-center gap-3">
                <img :src="user.image" alt="avatar" class="w-8 h-8 rounded-full object-cover" />
                <span class="font-medium text-gray-800">{{ user.userName }}</span>
              </div>
            </li>
          </ul>
        </div>

        <!-- Nếu tìm không thấy -->
        <div v-else-if="searchPhone" class="text-gray-500 text-center mt-4">
          Không tìm thấy người dùng
        </div>
      </div>
    </div>

    <!-- Dialog chi tiết user -->
<!-- Dialog chi tiết user -->
<div v-if="showUserDialog" class="fixed inset-0 flex justify-center items-center z-50">
  <div class="absolute inset-0 bg-black opacity-40" @click="showUserDialog = false"></div>

  <div class="relative bg-white p-6 rounded-xl w-96 shadow-lg z-50">
    <h3 class="text-xl font-bold mb-4">Thông tin tài khoản</h3>
    <div class="w-full h-20 bg-amber-200">

      <div class="pt-15 pl-4 flex">
     <div class="w-20 h-20 rounded-full bg-gray-300 flex items-center justify-center text-white text-2xl font-bold">
  <template v-if="selectedUser?.image">
    <img 
      :src="selectedUser.image" 
      alt="avatar" 
      class="w-20 h-20 rounded-full object-cover"
    />
  </template>
  <template v-else>
    {{ selectedUser?.userName?.charAt(0).toUpperCase() }}
  </template>
</div>

<div class="pt-8 pl-2"> <h3>{{ selectedUser?.userName }}</h3></div>

</div>
 <div class="flex justify-start mt-4 gap-3">
      <!-- Nếu là người lạ -->
      <button 
        v-if="selectedUser?.status === 'Người lạ' || !selectedUser?.status"
        class="px-4 py-2 bg-gray-400 text-white rounded-lg hover:bg-gray-500"
      @click="addFriend(selectedUser?.phone || '')"

      >
        Thêm bạn bè
      </button>

      <!-- Nếu đang chờ -->
      <button 
        v-else-if="selectedUser?.status === 'PENDING'"
        class="px-4 py-1 bg-gray-400 text-white rounded-lg hover:bg-gray-500"
      
      >
        Hủy yêu cầu
      </button>

      <!-- Nếu đã là bạn -->
      <button 
        v-else-if="selectedUser?.status === 'ACCEPTED'"
        class="px-4 py-1 bg-gray-400 text-white rounded-lg hover:bg-gray-500"
       
      >
        Hủy bạn bè
      </button>
      </div>
<hr class="mt-2">

    </div>
    <div class="pt-30">
   
    <h4 class="text-x font-bold mb-2">Thông tin cá nhân</h4>
    <p><b>SĐT:</b> {{ selectedUser?.phone }}</p>
      <p><b>Giới Tính:</b> {{ selectedUser?.sex }}</p>
      <p><b>Ngày Sinh:</b> {{ selectedUser?.dateOfBirth }}</p>
    <p><b>Trạng thái:</b> {{ selectedUser?.status || 'Người lạ' }}</p>

   
      <!-- Nút đóng -->
      <button 
        class="px-4 py-2 bg-gray-400 text-white rounded-lg hover:bg-gray-500"
        @click="showUserDialog = false"
      >
        Đóng
      </button>
    </div>
  </div>
</div>

  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, computed } from "vue";
import { getFriends } from "../../service/friendService";
import userService from "../../service/user";
import type { UserResponse } from "../../types/user";
import type { FriendResponse } from "../../types/friend";
import type { FriendRRequest, FriendRResponse } from "../../types/friendR";
import FriendService from "../../service/FriendRService";

// State
const friends = ref<FriendResponse[]>([]);
const search = ref("");
const searchPhone = ref("");
const searchResults = ref<UserResponse[]>([]);
const showAddFriendDialog = ref(false);

// Thêm state cho dialog chi tiết user
const selectedUser = ref<UserResponse | null>(null);
const showUserDialog = ref(false);

const friendRs = ref<FriendRResponse | null>(null);

// Emit event chọn bạn
const emit = defineEmits<{ (e: "selectFriend", friend: FriendResponse): void }>();
function selectFriend(friend: FriendResponse) {
  emit("selectFriend", friend);
}

// Đóng dialog thêm bạn
function closeDialog() {
  showAddFriendDialog.value = false;
  searchPhone.value = "";
  searchResults.value = [];
}

// Hàm mở dialog chi tiết user
function openUserDialog(user: UserResponse) {
  selectedUser.value = user;
  showUserDialog.value = true;
}

const addFriend = async (sdt: string) => {
  console.log("Gọi addFriend với sdt:", sdt);
  try {
    const data = await FriendService.makeFriend({ sdt } as FriendRRequest);
    console.log("Response từ backend:", data);
    friendRs.value = data;
    showUserDialog.value = false;
    closeDialog();
  } catch (err) {
    console.error("Thêm bạn thất bại:", err);
  }
};

// Hàm tìm kiếm theo số điện thoại
const timKiem = async (phone: string) => {
  const query = phone.trim();
  if (!query) {
    searchResults.value = [];
    return;
  }
  try {
    const rp = await userService.getUser(query);
    console.log("Kết quả tìm kiếm:", rp);

    if (rp) {
      searchResults.value = Array.isArray(rp) ? rp : [rp];
    } else {
      searchResults.value = [];
    }
  } catch (err) {
    console.error("Lỗi tìm kiếm:", err);
    searchResults.value = [];
  }
};

// Lấy danh sách bạn bè
onMounted(async () => {
  try {
    const res = await getFriends();
    friends.value = res;
  } catch (err) {
    console.error("Lấy danh sách bạn bè thất bại:", err);
  }
});

// Lọc danh sách bạn bè theo search
const filteredFriends = computed(() => {
  if (!search.value) return friends.value;
  return friends.value.filter((f) =>
    f.name.toLowerCase().includes(search.value.toLowerCase())
  );
});
</script>
