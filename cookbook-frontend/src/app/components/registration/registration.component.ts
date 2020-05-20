import { Component, OnInit, Injectable} from '@angular/core';
import { UserRequest } from 'src/app/models/userRequest';
import { Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss'],
  providers: [UserService]
})
export class RegistrationComponent implements OnInit {
  
  private userRequest: UserRequest[];
  
 constructor(
    public userService: UserService) { 
    console.log("sgsw");
    }

  ngOnInit() {
  }


  registerUser() {
    let firstName = (<HTMLInputElement>document.getElementById('firstName')).value;
    let lastName = (<HTMLInputElement>document.getElementById('lastName')).value;
    let dateOfBirth = (<HTMLInputElement>document.getElementById('dateOfBirth')).value;
    let email = (<HTMLInputElement>document.getElementById('email')).value;
    let city = (<HTMLSelectElement>document.getElementById('city')).value;
    let gender = (<HTMLSelectElement>document.getElementById('gender')).value;
    let role = (<HTMLSelectElement>document.getElementById('role')).value;
    
    //let password = (<HTMLInputElement>document.getElementById('password')).value;
   // let confirmPassword = (<HTMLInputElement>document.getElementById('confirmPassword')).value;


    let userReqest = new UserRequest(firstName , "aa", "aa",lastName,dateOfBirth, email, city, gender, role);
    var eachProduct = 
    {
      "firstName": "Azraa",
      "lastName": "Azraa",
      "email": "azraa1a@nnesto.ba",
      "password": "azra",
      "passwordConfirm": "azra"
    };
   
    //this.userService.registerUser(eachProduct);
    };
}


