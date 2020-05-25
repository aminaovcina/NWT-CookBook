import { Component, OnInit, Inject } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import { User } from 'src/app/models/user';
import { componentFactoryName } from '@angular/compiler';
@Component({
  selector: 'app-profile-edit',
  templateUrl: './profile-edit.component.html',
  styleUrls: ['./profile-edit.component.scss'],
  providers: [UserService]
})
export class ProfileEditComponent implements OnInit {
  private user: User = JSON.parse(sessionStorage.getItem('account')).user;
  userName = this.user.firstName + " " + this.user.lastName;
  mail = this.user.email;
  buttonDisabled: boolean ;
  buttonDisabled1: boolean ;
  buttonDisabled2: boolean ;

  constructor(private userService: UserService) { }

  ngOnInit() {
   this.user = JSON.parse(sessionStorage.getItem('account')).user;
    
    (<HTMLInputElement>document.getElementById('firstName')).value =  this.user.firstName;
    (<HTMLInputElement>document.getElementById('lastName')).value = this. user.lastName;

    (<HTMLInputElement>document.getElementById('email')).value =  this.user.email;
    (<HTMLSelectElement>document.getElementById('city')).value =  this.user.city;
    (<HTMLSelectElement>document.getElementById('gender')).value =  this.user.gender;
    (<HTMLInputElement>document.getElementById('dateOfBirth')).value =  this.user.date_Of_Birth;

   
    
    if(this.user.role.roleId == 2) {
      (<HTMLSelectElement>document.getElementById('role')).value = "2";
      this.buttonDisabled = true;
      this.buttonDisabled1 = true;
      this.buttonDisabled2 = true;
    }
    if(this.user.role.roleId == 1) {
      (<HTMLSelectElement>document.getElementById('role')).value = "1";


      this.buttonDisabled = false;
      this.buttonDisabled1 = false;
      this.buttonDisabled2 = false;
    }
    
  };
  logoutUser() {
    this.userService.logoutUser().subscribe(respones => {});
    sessionStorage.clear()
    componentFactoryName
  }

  saveChanges()
  {
    this.user.firstName = (<HTMLInputElement>document.getElementById('firstName')).value;
    this.user.lastName = (<HTMLInputElement>document.getElementById('lastName')).value;
    this.user.date_Of_Birth = (<HTMLInputElement>document.getElementById('dateOfBirth')).value;
    this.user.email = (<HTMLInputElement>document.getElementById('email')).value;
    this.user.city = (<HTMLSelectElement>document.getElementById('city')).value;
    this.user.gender = (<HTMLSelectElement>document.getElementById('gender')).value;
    this.user.role.roleId = Number.parseInt( (<HTMLSelectElement>document.getElementById('role')).value);
    let password = (<HTMLInputElement>document.getElementById('currentPassword')).value;
    let newPassword = (<HTMLInputElement>document.getElementById('password')).value;
    let confirmPassword = (<HTMLInputElement>document.getElementById('confirmPassword')).value;

    if(password.length==0) password = null;
    if(newPassword.length==0) newPassword = null;
    if(confirmPassword.length==0) confirmPassword = null;

    var eachProduct = 
    {
      "firstName": this.user.firstName,
      "lastName": this.user.lastName,
      "email": this.user.email,
      "role": {"roleId": this.user.role.roleId},
      "gender": this.user.gender,
      "city": this.user.city,
      "dateOfBirth": this.user.date_Of_Birth,
      "currentPassword": password,
      "password": newPassword,
      "passwordConfirm": confirmPassword
    };
  
    this.userService.saveChanges(eachProduct).subscribe(response => {
      let account =  JSON.parse(sessionStorage.getItem('account'));
      account.user = this.user;
      account.token  = response.body.token;
      sessionStorage.setItem('account', JSON.stringify( account));
      this.ngOnInit()
      
    },
    err => console.error(err));

  };

  
}
