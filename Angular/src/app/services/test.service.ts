import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = 'http://localhost:8080/api/test/';

@Injectable({
  providedIn: 'root'
})
export class TestService {

  constructor(private http: HttpClient) { }

  getPublicContent(): Observable<string> {
    return this.http.get(API_URL + 'public', { responseType: 'text' });
  }

  getAdminBoard(): Observable<string> {
    return this.http.get(API_URL + 'admin', { responseType: 'text' });
  }

  getModeratorBoard(): Observable<string> {
    return this.http.get(API_URL + 'mod', { responseType: 'text' });
  }

  getUserBoard(): Observable<string> {
    return this.http.get(API_URL + 'guest', { responseType: 'text' });
  }

}
