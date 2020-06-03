import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import { HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  providers: [UserService]
})
export class LoginComponent implements OnInit {

  @ViewChild('alert', { static: true }) alert: ElementRef

  constructor(private userService: UserService) {
    
   }

  ngOnInit() {

  }
  errorHandler() {
    alert("Neuspjesan login!")
  }

  loginUser() {
    let email = (<HTMLInputElement>document.getElementById('email')).value;
    let password = (<HTMLInputElement>document.getElementById('password')).value;

    var eachProduct = 
    {
      "username": email,
      "password": password
    };
    
    if(email=="" || password=="") alert("Popunite sva polja!")

    this.userService.loginUser(eachProduct).subscribe(account => {
      sessionStorage.clear()
      sessionStorage.setItem('account', JSON.stringify(account.body));
      alert("Uspjesan login!")},
      console.error
    )
  };
}

