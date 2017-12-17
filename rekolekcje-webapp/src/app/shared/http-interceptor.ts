import { forwardRef, Inject, Injectable, Injector } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/throw'
import 'rxjs/add/operator/catch';
import { AuthService } from '../auth/auth.service';

@Injectable()
export class AuthHttpInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Clone the request to add the new header.
    const authReq = req.clone();
    authReq.headers.append('Content-Type', 'application/json');
    authReq.headers.append('Accept', 'application/json');
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
