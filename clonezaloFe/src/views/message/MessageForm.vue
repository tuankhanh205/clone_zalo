<script setup lang="ts">
import { ref, watch, nextTick, computed, onMounted, onBeforeUnmount } from 'vue'
import { messageService } from '../../service/messageService'
import { connectSocket,getSocket } from '../../service/socket/socketService'
import type { MessagesResponse } from '../../types/messages'

const props = defineProps<{
  receiverId: number
  userReceiverName?: string
  receiverAvatar?: string
}>()

const messages = ref<MessagesResponse[]>([])
const userId = Number(localStorage.getItem('userId'))
const chatContainer = ref<HTMLElement | null>(null)
const newMessage = ref('')
const newFile = ref<File | null>(null)
const fileInput = ref<HTMLInputElement | null>(null)

let socket: any = null

// script setup
const latestMessageFromReceiver = computed(() => {
  return messages.value
    .filter(msg => msg.userSenderId === props.receiverId)
    .slice(-1)[0]  // lấy tin nhắn mới nhất
})

const displayName = computed(() => latestMessageFromReceiver.value?.userSenderName || props.userReceiverName || '')


// Scroll xuống cuối
const scrollToBottom = () => {
  if (chatContainer.value) chatContainer.value.scrollTop = chatContainer.value.scrollHeight
}

// Load tin nhắn
const loadMessages = async () => {
  try {
    const res = await messageService.listMessages(props.receiverId)
    messages.value = res
    await nextTick()
    scrollToBottom()
  } catch (err) {
    console.error('Lấy danh sách tin nhắn lỗi:', err)
  }
}

// Watch khi đổi friend
watch(() => props.receiverId, () => loadMessages(), { immediate: true })

// Mount: kết nối socket
onMounted(() => {
  socket = connectSocket()
  if (!socket) return

  socket.on('newMessage', (msg: MessagesResponse) => {
    if (msg.userSenderId === props.receiverId || msg.userReceiverId === props.receiverId) {
      messages.value.push(msg)
      nextTick(() => scrollToBottom())
    }
  })
})

onBeforeUnmount(() => {
  if (socket) socket.off('newMessage')
})

// Gửi tin nhắn
const sendMessage = async () => {
  if (!newMessage.value.trim() && !newFile.value) return

  try {
    // Gọi sendMessage từ service, truyền file luôn
    const sent = await messageService.sendMessage(
      props.receiverId,
      newMessage.value,
      newFile.value
    )

    // Gửi socket
    if (socket) socket.emit('sendMessage', sent)

    // Push vào UI
    messages.value.push(sent)
    newMessage.value = ''
    newFile.value = null
    if (fileInput.value) fileInput.value.value = ''
    await nextTick()
    scrollToBottom()
  } catch (err) {
    console.error('Gửi tin nhắn lỗi:', err)
  }
}


// Chọn file
const handleFileChange = (e: Event) => {
  const target = e.target as HTMLInputElement
  if (target.files && target.files[0]) newFile.value = target.files[0]
}

// Mở file picker
const openFilePicker = () => {
  if (fileInput.value) fileInput.value.click()
}
</script>

<template>
  <div class="chat-container">
    <!-- Header -->
    <div class="chat-header">
      <div class="user-info">
        <img :src="props.receiverAvatar || '/src/assets/image/dungnguyen.jpg'" alt="avatar" class="user-avatar" />
        <div class="user-name">{{ displayName }}</div>
      </div>
       <div class="call-actions flex gap-2">
    <button class="call-btn w-10 h-10 flex items-center justify-center rounded-full hover:bg-gray-200">📞</button>
    <button class="video-call-btn w-10 h-10 flex items-center justify-center rounded-full hover:bg-gray-200">🎥</button>
  </div>
    </div>

    <!-- Chat messages -->
    <div ref="chatContainer" class="messages-area">
      <div v-for="(msg, index) in messages" :key="index"
           :class="['message', msg.userSenderId === userId ? 'sent' : 'received']">
        <div class="message-content flex items-start space-x-2">
          <img v-if="msg.userSenderId !== userId"
               :src="msg.imageReceiverName"
               alt="avatar"
               class="user-avatar w-8 h-8 rounded-full" />
          <div>
            <div class="sender-name font-semibold text-sm">
              {{ msg.userSenderId === userId ? 'Bạn' : msg.userSenderName }}
            </div>
            <div class="message-text">{{ msg.message }}</div>
            <img v-if="msg.image" :src="msg.image" class="message-image mt-1 rounded" />
          </div>
        </div>
      </div>
    </div>

    <!-- Input gửi tin nhắn -->
    <div class="message-input-area">
      <button @click="openFilePicker" class="attach-btn">📎</button>
      <input v-model="newMessage" @keyup.enter="sendMessage" type="text" placeholder="Nhập tin nhắn..." class="message-input" />
      <button @click="sendMessage" class="send-btn">➤</button>
      <input type="file" @change="handleFileChange" class="hidden" ref="fileInput" />
    </div>
  </div>
</template>

<style scoped>
.chat-container {
  display: flex;
  flex-direction: column;
  height: 100%;
  background-color: #f5f5f5;
  border-radius: 12px;
  overflow: hidden;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.chat-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px 16px;
  background-color: #ffffff;
  border-bottom: 1px solid #eaeaea;
}

.user-info {
  display: flex;
  align-items: center;
  gap: 12px;
}

.user-avatar {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  object-fit: cover;
}

.user-name {
  font-weight: 600;
  font-size: 16px;
  color: #333;
}

.call-actions {
  display: flex;
  gap: 8px;
}

.call-btn, .video-call-btn {
  width: 36px;
  height: 36px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #f0f2f5;
  border: none;
  cursor: pointer;
  transition: background-color 0.2s;
}

.call-btn:hover, .video-call-btn:hover {
  background-color: #e4e6e9;
}

.call-icon, .video-icon {
  width: 18px;
  height: 18px;
  fill: #606770;
}

.messages-area {
  flex: 1;
  padding: 16px;
  overflow-y: auto;
  display: flex;
  flex-direction: column;
  gap: 12px;
  background-color: #f0f2f5;
}

.message {
  max-width: 70%;
}

.message.sent {
  align-self: flex-end;
}

.message.received {
  align-self: flex-start;
}

.message-content {
  padding: 12px 16px;
  border-radius: 18px;
  position: relative;
  word-break: break-word;
}

.message.sent .message-content {
  background-color: #1877f2;
  color: white;
  border-bottom-right-radius: 4px;
}

.message.received .message-content {
  background-color: white;
  color: #050505;
  border-bottom-left-radius: 4px;
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
}

.sender-name {
  font-size: 12px;
  font-weight: 600;
  margin-bottom: 4px;
  opacity: 0.8;
}

.message.sent .sender-name {
  color: rgba(255, 255, 255, 0.8);
}

.message.received .sender-name {
  color: #65676b;
}

.message-text {
  font-size: 14px;
  line-height: 1.4;
}

.message-image {
  max-width: 100%;
  border-radius: 8px;
  margin-top: 8px;
  max-height: 200px;
  object-fit: contain;
}

.message-time {
  font-size: 11px;
  text-align: right;
  margin-top: 4px;
  opacity: 0.7;
}

.message.sent .message-time {
  color: rgba(255, 255, 255, 0.7);
}

.message.received .message-time {
  color: #65676b;
}

.message-input-area {
  display: flex;
  align-items: center;
  padding: 12px 16px;
  background-color: white;
  border-top: 1px solid #eaeaea;
  gap: 8px;
}

.message-input {
  flex: 1;
  padding: 10px 16px;
  border-radius: 20px;
  border: none;
  background-color: #f0f2f5;
  outline: none;
  font-size: 14px;
  transition: background-color 0.2s;
}

.message-input:focus {
  background-color: #e4e6e9;
}

.attach-btn, .send-btn {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  border: none;
  cursor: pointer;
  background-color: transparent;
  transition: background-color 0.2s;
}

.attach-btn:hover, .send-btn:hover {
  background-color: #f0f2f5;
}

.send-btn {
  background-color: #1877f2;
  color: white;
}

.send-btn:hover {
  background-color: #166fe5;
}

.attach-icon, .send-icon {
  width: 20px;
  height: 20px;
}

.attach-icon {
  fill: #65676b;
}

.send-icon {
  fill: white;
}

/* Custom scrollbar */
.messages-area::-webkit-scrollbar {
  width: 6px;
}

.messages-area::-webkit-scrollbar-track {
  background: #f1f1f1;
}

.messages-area::-webkit-scrollbar-thumb {
  background: #c1c1c1;
  border-radius: 3px;
}

.messages-area::-webkit-scrollbar-thumb:hover {
  background: #a8a8a8;
}
</style>