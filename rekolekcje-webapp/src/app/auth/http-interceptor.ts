import { forwardRef, Inject, Injectable, Injector } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/throw'
import 'rxjs/add/operator/catch';
import { AuthService } from './auth.service';

@Injectable()
export class AuthHttpInterceptor implements HttpInterceptor {

  constructor(private injector: Injector) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Clone the request to add the new header.
    // const authReq = req.clone();
    // authReq.headers.append('Content-Type', 'application/json');
    // authReq.headers.append('Accept', 'application/json');
    const authService = this.injector.get(AuthService);
    const authReq = req.clone(
      { setHeaders: { Authorization: 'Bearer ' + authService.getToken() } }
    );

    return next.handle(authReq)
      .catch((error, caught) => {
        //intercept the respons error and displace it to the console
        console.log('Error Occurred');
        console.log(error);
        //return the error to the method that called it
        return Observable.throw(error);
      }) as any;
  }

}
