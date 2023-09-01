import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class KontoService {
  private apiUrl = 'http://localhost:8080/api/bezahl/v1';

  constructor(private http: HttpClient) {}

  getKontostand(kundenId: string): Observable<number> {
    const url = `${this.apiUrl}/getKontostand`;
    const params = { kundenId: kundenId };
    return this.http.get<number>(url, { params: params });
  }

  updateKontostand(kundenId: string, neuerKontostand: number): Observable<any> {
    const url = `${this.apiUrl}/update`;
    const data = { kundenId: kundenId, kontostand: neuerKontostand };
    return this.http.post(url, data);
  }
}
