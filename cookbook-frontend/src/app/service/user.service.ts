import { Http, Response } from "@angular/http";
import { Headers, RequestOptions } from '@angular/http';
import { User } from '../models/user';
import { UserRequest } from '../models/userRequest';
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http'
import { Observable } from 'rxjs';


@Injectable()
export class UserService {
  constructor(private http : HttpClient) {}
  private apiUrl = 'http://localhost:8070/users/register';
  private apiUrlLogin = 'http://localhost:8070/users/login';
 
  private handleError(error: any): Promise<Array<any>> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
    }

    registerUser(userRequest: Object): Observable<HttpResponse<User>> {
      let httpHeaders = new HttpHeaders({
        'Content-Type' : 'application/json',
        'Accept': 'application/json'
    });    
    return this.http.post<User>(this.apiUrl, userRequest,
        {
          headers: httpHeaders,
          observe: 'response'
        }
      );
    }
   
   loginUser(loginRequest: Object): Observable<HttpResponse<Account>> {
      let httpHeaders = new HttpHeaders({
        'Content-Type' : 'application/json',
        'Accept': 'application/json'
    });    
    return this.http.post<Account>(this.apiUrlLogin, loginRequest,
      {
        headers: httpHeaders,
        observe: 'response'
      }
    );
   }

    
   logoutUser():  Observable<HttpResponse<any>> {
     let apiUrlUserLogout = 'http://localhost:8070/logout';
      let httpHeaders = new HttpHeaders({
          'Content-Type' : 'application/json',
          'Accept': 'application/json'
      });    
      return this.http.post<any>(apiUrlUserLogout, 
        {
          headers: httpHeaders
        }
      );
  }


saveChanges(userRequest: Object): Observable<HttpResponse<User>> {
  let apiUrlSave = 'http://localhost:8070/users/update/' +  JSON.parse(sessionStorage.getItem('account')).user.id;

  let httpHeaders = new HttpHeaders({
    'Content-Type' : 'application/json',
    'Accept': 'application/json'
});    
return this.http.put<User>(apiUrlSave, userRequest,
    {
      headers: httpHeaders,
      observe: 'response'
    }
  );
}




}
  