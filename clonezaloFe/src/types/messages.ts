export interface MessagesRequest{
    message:string
}
export interface MessagesResponse{
     userSenderId:number
    userSenderName:string
    userReceiverId:number
    userReceiverName:string
    message:string
    image:string
    imageReceiverName:string
    seen:boolean
}