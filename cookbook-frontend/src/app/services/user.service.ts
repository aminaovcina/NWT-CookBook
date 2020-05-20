import { Http, Response } from "@angular/http";
import { Headers, RequestOptions } from '@angular/http';
import { User } from '../models/user';
import { UserRequest } from '../models/userRequest';
import { Injectable } from '@angular/core';

@Injectable()
export class UserService {
private apiUrl = '/register';
constructor(private http: Http) {
 }

registerUser(userRequest: UserRequest): Promise<Array<User>> {
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
}