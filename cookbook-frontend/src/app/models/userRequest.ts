export class UserRequest{
    id: number;
    firstName: string;
    lastName: string;
    password: string;
    confirmPassword: string;
    dateOfBirth: string;
    email: string;
    city: string;
    gender: string;
    role: string;

    constructor( firstName: string, 
        password: string,
        confirmPassword: string,
        lastName: string, 
        dateOfBirth: string,
        email: string,
        city: string,
        gender: string,
        role: string){

    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.email = email;
    this.city = city;
    this.gender = gender;
    this.role = role;
    this.password = password;
    this.confirmPassword = confirmPassword;
    }
}