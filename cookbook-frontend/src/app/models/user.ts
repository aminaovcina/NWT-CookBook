export class User{
    id: number;
    firstName: string;
    lastName: string;
    date_Of_Birth: string;
    email: string;
    city: string;
    gender: string;
    role: Role;
    token: string;

    constructor( firstName: string, lastName: string, date_Of_Birth: string,
        email: string,
        city: string,
        gender: string,
        role: Role,
        token: string){

    this.firstName = firstName;
    this.lastName = lastName;
    this.date_Of_Birth = date_Of_Birth;
    this.email = email;
    this.city = city;
    this.gender = gender;
    this.role = role;
    this.token = token
    }
}