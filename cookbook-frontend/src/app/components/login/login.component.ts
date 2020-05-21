import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/service/user.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  providers: [UserService]
})
export class LoginComponent implements OnInit {

  constructor(private userService: UserService) { }

  ngOnInit() {
  }

  loginUser() {
    let email = (<HTMLInputElement>document.getElementById('email')).value;
    let password = (<HTMLInputElement>document.getElementById('password')).value;


    var eachProduct = 
    {
      "username": email,
      "password": password
    };

    this.userService.loginUser(eachProduct).subscribe(account => {
      sessionStorage.setItem('account', JSON.stringify(account.body));

     
    },
    err => console.error(err));

  };
}

