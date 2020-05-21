import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler,  HttpInterceptor, HttpErrorResponse, HttpResponse, HttpSentEvent, HttpHeaderResponse, HttpProgressEvent, HttpUserEvent } from '@angular/common/http';
import { Observable, throwError, BehaviorSubject } from 'rxjs';
import { catchError, switchMap, finalize, take, filter, first } from 'rxjs/operators';

@Injectable()
export class headerInterceptor implements HttpInterceptor {

    constructor() { }

    tokenSubject: BehaviorSubject<string> = new BehaviorSubject<string>(null);

    intercept(request: HttpRequest<any>, newRequest: HttpHandler): Observable<HttpSentEvent | HttpHeaderResponse | HttpProgressEvent | HttpResponse<any> | HttpUserEvent<any> | any> {
       
        let account = JSON.parse(sessionStorage.getItem('account'));


        if (account) {
            request = request.clone({ setHeaders: { Authorization: `Bearer ${account.token}` } });
        }
        return newRequest.handle(request).pipe(
            catchError(err => {
                if (err instanceof HttpErrorResponse) {
                    switch ((<HttpErrorResponse>err).status) {
                        case 401: return <any>this.handle401Error(request, newRequest);
                    }
                } else {
                    return throwError(err);
                }
            }));
    }

    private addTokenToRequest(request: HttpRequest<any>, token: string): HttpRequest<any> {
        return request.clone({ setHeaders: { Authorization: `Bearer ${token}` } });
    }

    private handle401Error(request: HttpRequest<any>, next: HttpHandler) {
        //Implement logout
    }

}