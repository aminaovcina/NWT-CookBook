import { Component, OnInit} from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { UserRequest } from 'src/app/models/userRequest';


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss'],
  providers: [UserService]
})
export class RegistrationComponent implements OnInit {
  
  private userRequest: UserRequest[];
  private userService: UserService;

 // constructor(userService: UserService) { }

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


    let userReqest = new UserRequest(firstName , password, confirmPassword,lastName,dateOfBirth, email, city, gender, role);

    this.userService.registerUser(userReqest);
    };
}


