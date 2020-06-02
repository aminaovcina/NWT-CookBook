import { Component, OnInit, Injectable, ViewChild,ElementRef } from '@angular/core';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss'],
  providers: [UserService]
})
export class RegistrationComponent implements OnInit {
  
  @ViewChild('alert', { static: true }) alert: ElementRef;

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
    
    if(password!=confirmPassword)  alert("Ne preklapaju se pasword-i!")
  
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
   
   
    if  (!this.validateEmail(email)) {
      //Validacijski error
      alert("Pogresan format email-a!")
    }
    if  (!this.validatePassword(password)) {
      //Validacijski error
      alert("Pogresan format password-a!")
    }
    if(firstName=="" || lastName=="") alert("Popunite sva polja!")

    if (this.validateEmail(email) && this.validatePassword(password) && firstName!="" && lastName!="") {
      this.userService.registerUser(eachProduct).subscribe(response => {
        console.log('ima')
        alert("Uspjesna registracija!")
     
       
      },
      err =>{ console.error(err)
        alert("Neuspjesna registracija!" + " " + err)
        
      });
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

