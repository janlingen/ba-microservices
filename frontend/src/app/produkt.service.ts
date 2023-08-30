import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProduktService {
  private apiUrl = 'http://localhost:8080/api/produkt/v1/';

  constructor(private http: HttpClient) {}

  getProdukte(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl + 'getAll');
  }

  sendProduktInfo(produkt: any): Observable<any> {
    return this.http.post(this.apiUrl + 'create', produkt, {observe: 'response'});
  }
}
