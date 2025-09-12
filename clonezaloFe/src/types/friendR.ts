export interface FriendRRequest{
    sdt:string
    senderId:number
    receiver_id:number
    status:string
}
export interface FriendRResponse{
    id:number
    sdt:string
    image:string
    senderName:string
    receiveName:string
    status:string
    sentAt:string
}