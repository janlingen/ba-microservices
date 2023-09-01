import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InventarService {
  private apiUrl = 'http://localhost:8080/api/inventar/v1';

  constructor(private http: HttpClient) { }

  getInventar(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/getAll`);
  }

  erhoeheAnzahl(artikel: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/update`, artikel);
  }

  verringereAnzahl(artikel: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/update`, artikel);
  }
}
