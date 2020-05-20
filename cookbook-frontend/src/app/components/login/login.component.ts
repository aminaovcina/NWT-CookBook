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
      "username": "azraaa@nnesto.ba",
      "password": "azra"
    };

    this.userService.loginUser(eachProduct).subscribe(response => {
      console.log('ima')
      localStorage.setItem('token', JSON.stringify(response.body.token));
      console.log(localStorage.getItem('token'))
    },
    err => console.error(err));
  };

}

