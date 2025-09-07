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
            {{ friend.name[0].toUpperCase() }}
          </div>
          <span>{{ friend.name }}</span>
        </li>
      </ul>
      <div v-if="friends.length === 0" class="text-gray-400 mt-2">Chưa có bạn bè</div>
    </div>

    <!-- Dialog thêm bạn -->
    <div 
      v-if="showAddFriendDialog" 
      class="fixed inset-0 flex justify-center items-center z-50"
    >
      <!-- Overlay -->
      <div class="absolute inset-0 bg-black opacity-40"></div>

      <!-- Nội dung dialog -->
      <div class="relative bg-white p-6 rounded-2xl w-96 max-h-[80vh] overflow-y-auto shadow-2xl z-50">
        <h3 class="text-xl font-bold mb-4 text-center">Thêm bạn</h3>

        <input 
          type="text" 
          placeholder="Nhập số điện thoại" 
          class="w-full p-3 border rounded-lg mb-4 focus:outline-none focus:ring-2 focus:ring-blue-400"
        />

        <div class="flex justify-end gap-3 mb-4">
          <button 
            class="px-4 py-2 bg-gray-200 rounded-lg hover:bg-gray-300"
            @click="showAddFriendDialog = false"
          >
            Hủy
          </button>
          <button 
            class="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600"
          >
            Tìm
          </button>
        </div>

        <!-- Gợi ý bạn bè -->
        <div>
          <h4 class="font-semibold mb-2 text-gray-700">Gợi ý bạn bè</h4>
          <ul class="space-y-2">
            <li 
              v-for="suggested in suggestedFriends" 
              :key="suggested.id"
              class="flex items-center justify-between p-2 bg-gray-50 hover:bg-gray-100 rounded-lg"
            >
              <div class="flex items-center gap-3">
                <div class="w-10 h-10 bg-blue-400 rounded-full flex items-center justify-center text-white font-bold">
                  {{ suggested.name[0].toUpperCase() }}
                </div>
                <span class="font-medium text-gray-800">{{ suggested.name }}</span>
              </div>
              <button class="px-3 py-1 bg-green-500 text-white rounded-lg hover:bg-green-600 text-sm">
                Thêm
              </button>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, computed } from 'vue';
import { getFriends } from '../../service/friendService';

const friends = ref<any[]>([]);
const search = ref('');
const showAddFriendDialog = ref(false);

// Danh sách gợi ý bạn bè cứng
const suggestedFriends = ref([
  { id: 1, name: 'Nguyễn Văn Đạt' },
  { id: 2, name: 'Nguyễn Tuấn Anh' },
  { id: 3, name: 'Phạm Tuấn Hà' },
  { id: 4, name: 'Lê Tiến Hưởng' },
  { id: 5, name: 'Nguyễn Thế Đạt' },
]);

const emit = defineEmits<{ (e: 'selectFriend', friend: any): void }>();
function selectFriend(friend: any) {
  emit('selectFriend', friend);
}

onMounted(async () => {
  try {
    const res = await getFriends();
    friends.value = res;
    console.log(res)
  } catch (err) {
    console.error('Lấy danh sách bạn bè thất bại:', err);
  }
});

const filteredFriends = computed(() => {
  if (!search.value) return friends.value;
  return friends.value.filter(f => f.name.toLowerCase().includes(search.value.toLowerCase()));
});
</script>
