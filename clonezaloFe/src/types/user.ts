export interface UserResponse{
    id:number
    userName:string
    image:string
    phone:string
    status:string
    sex:string
    dateOfBirth:Date
}
export interface UserRequest{
    userName:string
    image:string
    sex:string
    dateOfBirth:Date
}