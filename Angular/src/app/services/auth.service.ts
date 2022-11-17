import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {BehaviorSubject, Observable, Subject} from "rxjs";
import {JwtResponse} from "../interfaces/LoginResponse";
import {UserDetails} from "../interfaces/user-details";
import {UserSession} from "../model/UserSession";

const AUTH_API = 'http://localhost:8080/api/auth/';
const JWT_KEY = 'jwt';
const USER_KEY = 'auth-user';
const HTTP_OPTIONS = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  loginSubject: BehaviorSubject<any> = new BehaviorSubject<any>(this.getUser());

  constructor(private httpClient: HttpClient) {
  }

  saveData(jwtResponse: JwtResponse) {
    window.sessionStorage.removeItem(JWT_KEY);
    window.sessionStorage.setItem(JWT_KEY, jwtResponse.token);

    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(jwtResponse.user));
  }

  login(username: string, password: string): Observable<JwtResponse> {
    return this.httpClient.post<JwtResponse>(AUTH_API + 'log-in', {username, password}, HTTP_OPTIONS)
  }

  logOut(): void {
    window.sessionStorage.clear();
  }

  public getJWT(): string | null {
    return window.sessionStorage.getItem(JWT_KEY);
  }

  public getUser(): UserSession {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }
    return new UserSession();
  }

  register(username: string, email: string, password: string): Observable<any> {
    return this.httpClient.post(AUTH_API + 'signup', {
      username,
      email,
      password
    }, HTTP_OPTIONS);
  }
}
