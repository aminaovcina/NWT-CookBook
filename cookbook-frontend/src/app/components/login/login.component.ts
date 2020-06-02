import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { UserService } from 'src/app/service/user.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  providers: [UserService]
})
export class LoginComponent implements OnInit {

  @ViewChild('alert', { static: true }) alert: ElementRef

  constructor(private userService: UserService) { }

  ngOnInit() {

  }

  loginUser() {
    let email = (<HTMLInputElement>document.getElementById('email')).value;
    let password = (<HTMLInputElement>document.getElementById('password')).value;

   // alert("Pogresan password!")

    var eachProduct = 
    {
      "username": email,
      "password": password
    };

    this.userService.loginUser(eachProduct).subscribe(account => {
      sessionStorage.clear()
      sessionStorage.setItem('account', JSON.stringify(account.body));
      alert("Uspjesan login!")
     
    },
    err =>{ console.error(err)
     // if(err==)
      alert("Neuspjesan login!  " + err)
      
    });

  };
}

