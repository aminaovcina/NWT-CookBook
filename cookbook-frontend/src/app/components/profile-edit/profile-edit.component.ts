import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-profile-edit',
  templateUrl: './profile-edit.component.html',
  styleUrls: ['./profile-edit.component.scss'],
  providers: [UserService]
})
export class ProfileEditComponent implements OnInit {
 
  
  private user: User = JSON.parse(sessionStorage.getItem('account')).user;
  userName = this.user.firstName + " " +this.user.lastName;
  mail = this.user.email;

  constructor(private userService: UserService) { }

  ngOnInit() {
    
    (<HTMLInputElement>document.getElementById('firstName')).value = this.user.firstName;
    (<HTMLInputElement>document.getElementById('lastName')).value = this.user.lastName;

    (<HTMLInputElement>document.getElementById('email')).value = this.user.email;
    (<HTMLSelectElement>document.getElementById('grad')).value = this.user.city;


    (<HTMLSelectElement>document.getElementById('gender')).value = this.user.gender.toString();
   
    //(<HTMLInputElement>document.getElementById('date')).value = Date.parse(this.user.dateOfBirth).toString();
    //(<HTMLSelectElement>document.getElementById('role')).value = this.user.role;
    
  };

  saveChanges()
  {
    let firstName = (<HTMLInputElement>document.getElementById('firstName')).value;
    let lastName = (<HTMLInputElement>document.getElementById('lastName')).value;
    let dateOfBirth = (<HTMLInputElement>document.getElementById('dateOfBirth')).value;
    let email = (<HTMLInputElement>document.getElementById('email')).value;
    let city = (<HTMLSelectElement>document.getElementById('city')).value;
    let gender = (<HTMLSelectElement>document.getElementById('gender')).value;
    //let role = (<HTMLSelectElement>document.getElementById('role')).value;
    let password = (<HTMLInputElement>document.getElementById('password')).value;

    var eachProduct = 
    {
      "firstName": firstName,
      "lastName": lastName,
      "email": email,
      "password": password,
     // "role": role,
      "gender": gender,
      "city": city,
      "dateOfBirth": dateOfBirth
    };
  
    this.userService.saveChanges(eachProduct).subscribe(response => {
      console.log('ima')
      
    },
    err => console.error(err));
};

  
}
