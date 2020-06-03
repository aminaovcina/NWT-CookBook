import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Recipe } from '../models/recipe';
import { tap, catchError } from 'rxjs/operators';
import { Dish } from '../models/dish';
import { Category } from '../models/category';


@Injectable()
export class RecipeService {
  constructor(private http : HttpClient) {}
  private apiUrl = 'http://localhost:8080/recipe'; 
  private apiUrlDishs = 'http://localhost:8080/dish'; 
  private apiUrlCategories = 'http://localhost:8080/category';

getRecepti(): Observable<Recipe[]> {
  let account = JSON.parse(sessionStorage.getItem('account'));
  let httpHeaders = new HttpHeaders({
     'Authorization' : `Bearer  + ${account.token}`
    // 'Authorization' : `Bearer $2a$10$xxWNKZtEQoYwai5G3leoreO69cq6jpnLpc89HRwg7GZkjYbA3SXoO`
});   
  return this.http.get<Recipe[]>(this.apiUrl, {
    headers: httpHeaders
  }).pipe(
    tap(_ => console.log('fetched recipes'))
  
  );
}
getYourRecepti(): Observable<Recipe[]> {
  //let apiUrlUsersRecipes = 'http://localhost:8080/recipes/user/' +  JSON.parse(sessionStorage.getItem('account')).user_id;
  let apiUrlUsersRecipes = 'http://localhost:8080/recipes/user/1';
  // let account = JSON.parse(sessionStorage.getItem('account'));
  let httpHeaders = new HttpHeaders({
    //  'Authorization' : `Bearer  + ${account.token}`
    'Authorization' : `Bearer $2a$10$xxWNKZtEQoYwai5G3leoreO69cq6jpnLpc89HRwg7GZkjYbA3SXoO`
});   
  return this.http.get<Recipe[]>(apiUrlUsersRecipes, {
    headers: httpHeaders
  }).pipe(
    tap(_ => console.log('fetched users recipes'))
  
  );
}
deleteRecipe(userRequest: number): Observable<HttpResponse<any>> {
  let apiUrlDelete = 'http://localhost:8080/recipe/delete/' + userRequest;
  let httpHeaders = new HttpHeaders({
    'Content-Type' : 'application/json',
    'Accept': 'application/json',
    //'Authorization' : `Bearer  + ${account.token}`
    'Authorization' : `Bearer $2a$10$xxWNKZtEQoYwai5G3leoreO69cq6jpnLpc89HRwg7GZkjYbA3SXoO`
});    
return this.http.delete<any>(apiUrlDelete, 
    {
      headers: httpHeaders,
      observe: 'response'
    }
  );
}
getDishs(): Observable<Dish[]> {
  //let account = JSON.parse(sessionStorage.getItem('account'));
  let httpHeaders = new HttpHeaders({
     //'Authorization' : `Bearer  + ${account.token}`
    'Authorization' : `Bearer $2a$10$xxWNKZtEQoYwai5G3leoreO69cq6jpnLpc89HRwg7GZkjYbA3SXoO`
});   
  return this.http.get<Dish[]>(this.apiUrlDishs, {
    headers: httpHeaders
  }).pipe(
    tap(_ => console.log('fetched dishes'))
  
  );
}

getCategories(): Observable<Category[]> {
  //let account = JSON.parse(sessionStorage.getItem('account'));
  let httpHeaders = new HttpHeaders({
     //'Authorization' : `Bearer  + ${account.token}`
    'Authorization' : `Bearer $2a$10$xxWNKZtEQoYwai5G3leoreO69cq6jpnLpc89HRwg7GZkjYbA3SXoO`
});   
  return this.http.get<Category[]>(this.apiUrlCategories, {
    headers: httpHeaders
  }).pipe(
    tap(_ => console.log('fetched categories'))
  
  );
}
postRecipeCategory(userRequest: Object): Observable<HttpResponse<any>> {
  let apiUrlSave = 'http://localhost:8080/recipe/category/save';
  let httpHeaders = new HttpHeaders({
    'Content-Type' : 'application/json',
    'Accept': 'application/json',
    //'Authorization' : `Bearer  + ${account.token}`
    'Authorization' : `Bearer $2a$10$xxWNKZtEQoYwai5G3leoreO69cq6jpnLpc89HRwg7GZkjYbA3SXoO`
});    
return this.http.post<any>(apiUrlSave, userRequest,
    {
      headers: httpHeaders,
      observe: 'response'
    }
  );
}

postRecipe(userRequest: Object): Observable<HttpResponse<any>> {
  let apiUrlSave = 'http://localhost:8080/recipe/save';
  let httpHeaders = new HttpHeaders({
    'Content-Type' : 'application/json',
    'Accept': 'application/json',
    //'Authorization' : `Bearer  + ${account.token}`
    'Authorization' : `Bearer $2a$10$xxWNKZtEQoYwai5G3leoreO69cq6jpnLpc89HRwg7GZkjYbA3SXoO`
});    
return this.http.post<any>(apiUrlSave, userRequest,
    {
      headers: httpHeaders,
      observe: 'response'
    }
  );
}
}
