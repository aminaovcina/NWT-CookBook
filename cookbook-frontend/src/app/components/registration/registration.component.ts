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
      "role": {"roleId":role},
      "dateOfBirth": dateOfBirth
    };
   
    if (this.validateEmail(email) && this.validatePassword(password)) {
      this.userService.registerUser(eachProduct).subscribe(response => {
        console.log('ima')
       
      },
      err =>{ console.error(err)
        //this.toastr.error("Toastr Error Notification",'Error') 
      
        
      });
    } else {
      //Validacijski error
    }
   
  };
  validateEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
  }

  validatePassword(password) {
    var re = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{2,}$/;
    return re.test(String(password).toLowerCase());
  }
}

