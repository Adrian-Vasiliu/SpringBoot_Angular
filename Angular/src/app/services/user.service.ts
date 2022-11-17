import {Injectable} from '@angular/core';
import {User} from "../interfaces/user";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const userUrl = 'http://localhost:1234/api/users';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) {
  }

  getAll(): Observable<User[]> {
    return this.httpClient.get<User[]>(userUrl)
  }

  add(user: User): Observable<User> {
    return this.httpClient.post<User>(userUrl, user)
  }

  update(id: number, user: User): Observable<User> {
    return this.httpClient.put<User>(`${(userUrl)}/${id}`, user)
  }

  delete(id: number): Observable<any> {
    return this.httpClient.delete(`${(userUrl)}/${id}`)
  }

}
