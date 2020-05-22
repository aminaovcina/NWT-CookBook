export class User{
    id: number;
    firstName: string;
    lastName: string;
    dateOfBirth: string;
    email: string;
    city: string;
    gender: string;
    role: Role;
    token: string;

    constructor( firstName: string, lastName: string, dateOfBirth: string,
        email: string,
        city: string,
        gender: string,
        role: Role,
        token: string){

    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.email = email;
    this.city = city;
    this.gender = gender;
    this.role = role;
    this.token = token
    }
}