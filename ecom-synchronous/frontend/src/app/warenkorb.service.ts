import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class WarenkorbService {
  private apiUrl = 'http://localhost:8080/api/bestell/v1';
  private bestellungId: number | null = null;

  constructor(private http: HttpClient) {}

  getWarenkorb(): Observable<any> {
    return this.http
      .post<any>(`${this.apiUrl}/create`, localStorage.getItem('kundenId'))
      .pipe(
        map((response: { id: number | null }) => {
          this.bestellungId = response.id;
          return response;
        })
      );
  }

  addProduktToWarenkorb(
    name: string,
    preis: number,
    anzahl: number,
    kundenId: string | null
  ): Observable<any> {
    const updateContainer = {
      name,
      preis,
      anzahl,
      kundenId,
      bestellungId: this.bestellungId,
    };
    console.log(updateContainer);
    return this.http.post<any>(`${this.apiUrl}/update`, updateContainer);
  }

  bestellungAbschicken(kundenId: string | null): Observable<any> {
    const finishContainer = {
      kundenId,
      bestellungId: this.bestellungId,
    };
    return this.http.post<any>(`${this.apiUrl}/finish`, finishContainer);
  }
}
