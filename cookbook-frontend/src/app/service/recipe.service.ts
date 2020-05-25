import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Recipe } from '../models/recipe';

@Injectable()
export class RecipeService {

  constructor(private http : HttpClient) {}
  private apiUrl = 'http://localhost:8080/recipe';
  private handleError(error: any): Promise<Array<any>> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
    }

    getRecipes(): Observable<HttpResponse<any>> {
      let httpHeaders = new HttpHeaders({
        'Content-Type' : 'application/json',
        'Accept': 'application/json'
   });    
   return this.http.get<any>(this.apiUrl,
     {
       headers: httpHeaders,
       observe: 'response'
     }
   );
 }
// getRacuni() {
//   return this.http.get(this.apiUrl + 'api/PostojeciRacun/racuni')
// }
}
