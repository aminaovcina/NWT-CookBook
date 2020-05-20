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

    registerUser(userRequest: Object): Promise<Array<User>> {
       return this.http.post(this.apiUrl, JSON.stringify(userRequest), { headers: 
        { "Access-Control-Allow-Origin": "*",
        'Content-Type': 'application/json',
        "Access-Control-Allow-Methods": "GET, POST, DELETE, PUT, PATCH, HEAD" } })
      .toPromise()
      .then(response => response as User[])
      .catch(this.handleError);
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
/*
@Injectable({
  providedIn: 'root',
})
export class UserService {
private apiUrl = 'localhost:8060/register';
private apiUrlLogin = 'localhost:8060/login';

constructor(private http: Http) {
 }

registerUser(userRequest: Object): Promise<Array<User>> {
 let empHeaders = new Headers({ 'Content-Type': 'application/json' });
  return this.http.post(this.apiUrl, JSON.stringify(userRequest), { headers: empHeaders })
 .toPromise()
 .then(response => response.json as unknown as User[])
 .catch(this.handleError);
 }
 
private handleError(error: any): Promise<Array<any>> {
 console.error('An error occurred', error);
 return Promise.reject(error.message || error);
 }

loginUser(loginRequest: Object): Promise<Array<Account>> {
  let empHeaders = new Headers({ 'Content-Type': 'application/json' });
   return this.http.post(this.apiUrlLogin, JSON.stringify(loginRequest), { headers: empHeaders })
  .toPromise()
  .then(response => response.json as unknown as Account[])
  .catch(this.handleError);
  }
  
 
}*/
   }
  