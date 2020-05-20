export class UserRequest{
   
    password: string;
    confirmPassword: string;
    dateOfBirth: string;
    email: string;
    city: string;
    gender: string;
    role: string;

    constructor( 
        password: string,
        email: string){

   
    this.email = email;
    this.password = password;
    }
}