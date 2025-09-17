import axios from "axios";
import type { FriendRRequest, FriendRResponse } from "../types/friendR";

const apiClient = axios.create({
  baseURL: "http://localhost:8080/api/user",
});

// Interceptor tự động thêm token vào mọi request
apiClient.interceptors.request.use(config => {
  const token = localStorage.getItem("token");
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

const FriendRService = {
  makeFriend: async (friendRRequest: FriendRRequest): Promise<FriendRResponse> => {
    try {
      console.log("==== Gọi makeFriend ====");
      console.log("URL:", apiClient.defaults.baseURL + "/makefriend");
      console.log("Token:", localStorage.getItem("token"));
      console.log("Body gửi đi:", friendRRequest);

      const response = await apiClient.post<FriendRResponse>("/makefriend", friendRRequest);

      console.log("Status code:", response.status);
      console.log("Response data:", response.data);

      return response.data;
    } catch (err: any) {
      if (err.response) {
        console.error("FE nhận response lỗi:", err.response.status, err.response.data);
      } else {
        console.error("FE lỗi khi gửi request:", err.message);
      }
      throw err; // vẫn ném ra để FE bắt try/catch
    }
  },
  friendInvitation:async():Promise<FriendRResponse>=>{
    const response=await apiClient.get<FriendRResponse>(`listfriend`)
    console.log(response)
    return response.data

  },
  confirmInvitation:async(friendRRequest:FriendRRequest):Promise<FriendRResponse>=>{
    try{
      console.log("==== Gọi makeFriend ====");
      console.log("URL:", apiClient.defaults.baseURL + "/makefriend");
      console.log("Token:", localStorage.getItem("token"));
      console.log("Body gửi đi:", friendRRequest);

        const response=await apiClient.post<FriendRResponse>(`confirmInvitation`,friendRRequest)
       
      return response.data
    }
    catch(err:any){
      throw err
    }
      
  }
};

export default FriendRService;
