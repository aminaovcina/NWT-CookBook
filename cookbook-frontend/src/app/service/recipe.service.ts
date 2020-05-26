import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Recipe } from '../models/recipe';
import { tap, catchError } from 'rxjs/operators';


@Injectable()
export class RecipeService {
  constructor(private http : HttpClient) {}
  private apiUrl = 'http://localhost:8080/recipe'; 


getRecepti(): Observable<Recipe[]> {
  console.log("servis")
  let account = JSON.parse(sessionStorage.getItem('account'));
  let httpHeaders = new HttpHeaders({
     'Authorization' : `Bearer  + ${account.token}`
    // 'Authorization' : `Bearer  + aaaaaaaaaaaaaaaaa`
});   
  return this.http.get<Recipe[]>(this.apiUrl, {
    headers: httpHeaders
  }).pipe(
    tap(_ => console.log('fetched heroes'))
  
  );
}
}
