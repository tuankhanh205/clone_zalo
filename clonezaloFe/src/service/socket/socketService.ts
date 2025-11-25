import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import type { MessagesResponse } from '../../types/messages';

let stompClient: Client | null = null;

export const connectSocket = (
  userId: number,
  onMessage: (msg: MessagesResponse) => void
): Client => {
  // Tránh tạo client mới nếu đã tồn tại
  if (stompClient) return stompClient;

  const socket = new SockJS('http://localhost:8080/ws');
  stompClient = new Client({
    webSocketFactory: () => socket,
    reconnectDelay: 5000,
    onConnect: () => {
      console.log('✅ Connected to WebSocket');

      // Subscribe theo userId duy nhất
      stompClient?.subscribe(`/topic/messages/${userId}`, (message) => {
        const body = JSON.parse(message.body) as MessagesResponse;
        onMessage(body); // callback xử lý message
      });
    },
  });

  stompClient.activate();
  return stompClient;
};

export const sendMessageSocket = (msg: MessagesResponse) => {
  if (!stompClient || !stompClient.connected) return;

  // Chỉ gửi qua STOMP, không push vào messages.value ở đây
  stompClient.publish({
    destination: '/app/chat',
    body: JSON.stringify(msg),
  });
};

export const disconnectSocket = () => {
  stompClient?.deactivate();
  stompClient = null;
};
