import { Component, OnInit, Injectable} from '@angular/core';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss'],
  providers: [UserService]
})
export class RegistrationComponent implements OnInit {
  
  constructor(private userService: UserService) { }

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
    let password = (<HTMLInputElement>document.getElementById('password')).value;
    let confirmPassword = (<HTMLInputElement>document.getElementById('confirmPassword')).value;
    
  
    var eachProduct = 
    {
      "firstName": firstName,
      "lastName": lastName,
      "email": email,
      "password": password,
      "passwordConfirm": confirmPassword,
      "gender": gender.toString(),
      "city": city.toString(),
      "role": {"roleId": role},
      "date_Of_Birth": dateOfBirth
    };
   
    this.userService.registerUser(eachProduct).subscribe(response => {
      console.log('ima')
     
    },
    err =>{ console.error(err)
      //this.toastr.error("Toastr Error Notification",'Error') 
    
      
    });
  };
}

