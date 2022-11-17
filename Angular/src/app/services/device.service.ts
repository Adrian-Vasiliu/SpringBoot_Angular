import {Injectable} from '@angular/core';
import {Device} from "../interfaces/device";
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";
import {PageResponse} from "../interfaces/page-response";

const DEVICES_URL = 'http://localhost:8080/api/devices';

@Injectable({
  providedIn: 'root'
})
export class DeviceService {

  constructor(private httpClient: HttpClient) {
  }

  get(params: any): Observable<PageResponse> {
    return this.httpClient.get<PageResponse>(DEVICES_URL, {params});
  }

  getById(id: number): Observable<Device> {
    return this.httpClient.get<Device>(`${(DEVICES_URL)}/${id}`);
  }

  add(device: Device): Observable<Device> {
    return this.httpClient.post<Device>(DEVICES_URL, device);
  }

  update(id: number, device: Device): Observable<Device> {
    return this.httpClient.put<Device>(`${(DEVICES_URL)}/${id}`, device);
  }

  delete(id: number): Observable<any> {
    return this.httpClient.delete<void>(`${(DEVICES_URL)}/${id}`);
  }

}
