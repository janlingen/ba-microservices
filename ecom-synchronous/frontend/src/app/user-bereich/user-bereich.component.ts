import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login.service';
import { KontoService } from '../konto.service';

@Component({
  selector: 'app-user-bereich',
  templateUrl: './user-bereich.component.html',
  styleUrls: ['./user-bereich.component.scss'],
})
export class UserBereichComponent implements OnInit {
  isLoggedIn: boolean = false;
  kontostand: number = 0;

  constructor(private loginService: LoginService, private kontoService : KontoService) {}

  ngOnInit(): void {
    this.loginService.isLoggedInUser$.subscribe((loggedIn) => {
      this.isLoggedIn = loggedIn;
    });
    this.loginService.isLoggedInUser$.subscribe((loggedIn) => {
      this.isLoggedIn = loggedIn;
      if (loggedIn) {
        this.getKontostand();
      }
    });
  }

  getKontostand(): void {
    const kundenId = localStorage.getItem('kundenId');
    if (kundenId) {
      this.kontoService.getKontostand(kundenId).subscribe((kontostand) => {
        this.kontostand = kontostand;
      });
    }
  }

  erhoeheKontostand(): void {

    const kundenId = localStorage.getItem('kundenId');
    if (kundenId) {
      const neuerKontostand = this.kontostand + 100;
      this.kontoService.updateKontostand(kundenId, neuerKontostand).subscribe(() => {
        this.kontostand = neuerKontostand;
      });
    }
  }

  logout(): void {
    localStorage.removeItem('kundenId');
    this.isLoggedIn = false;
  }
}
