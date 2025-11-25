import axios from "axios";

export const getFriends = async () => {
  const token = sessionStorage.getItem("token");
  if (!token) throw new Error("Token không tồn tại. Vui lòng đăng nhập.");

  const response = await axios.get("http://localhost:8080/api/user/friendsList", {
    headers: { Authorization: `Bearer ${token}` },
  });
  console.log(response)

  return response.data; // trả về luôn data
};
