import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ZustellungService {
  private apiUrl = 'http://localhost:8080/api/zustell/v1';

  constructor(private http: HttpClient) { }

  getZustellungen(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/getAll`);
  }

  zugestellt(zustellungId: number): Observable<void> {
    const url = `${this.apiUrl}/finish`;
    return this.http.post<void>(url, zustellungId);
  }
  
}
