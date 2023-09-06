import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private userApiUrl = 'http://localhost:8080/api/user/v1';
  private mitarbeiterApiUrl = 'http://localhost:8080/api/mitarbeiter/v1';
  private kundenId: string | null = '';
  private isLoggedInUserSubject = new BehaviorSubject<boolean>(false);
  private mitarbeiterId: string | null = '';
  private isLoggedInMitarbeiterSubject = new BehaviorSubject<boolean>(false);

  isLoggedInUser$ = this.isLoggedInUserSubject.asObservable();
  isLoggedInMitarbeiter$ = this.isLoggedInMitarbeiterSubject.asObservable();

  constructor(private http: HttpClient) {
    this.kundenId = localStorage.getItem('kundenId');
    this.isLoggedInUserSubject.next(!!this.kundenId);
    this.mitarbeiterId = localStorage.getItem('mitarbeiterId');
    this.isLoggedInMitarbeiterSubject.next(!!this.mitarbeiterId);
  }

  loginUser(username: string, passwort: string): Observable<any> {
    const params = new HttpParams()
      .set('username', username)
      .set('passwort', passwort);
    return this.http.get<JSON | null>(`${this.userApiUrl}/login`, { params });
  }

  registrierenUser(userData: any): Observable<any> {
    return this.http.post<JSON | null>(`${this.userApiUrl}/create`, userData);
  }

  loginMitarbeiter(username: string, passwort: string): Observable<any> {
    const params = new HttpParams()
      .set('username', username)
      .set('passwort', passwort);
    return this.http.get<JSON | null>(`${this.mitarbeiterApiUrl}/login`, { params });
  }


  setLoggedInUser(isLoggedIn: boolean) {
    this.isLoggedInUserSubject.next(isLoggedIn);
  }

  setLoggedInMitarbeiter(isLoggedIn: boolean) {
    this.isLoggedInMitarbeiterSubject.next(isLoggedIn);
  }
}
