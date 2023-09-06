import { Component } from '@angular/core';
import { LoginService } from '../login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-mitarbeiter-bereich',
  templateUrl: './mitarbeiter-bereich.component.html',
  styleUrls: ['./mitarbeiter-bereich.component.scss'],
})
export class MitarbeiterBereichComponent {
  isLoggedIn: boolean = false;
  username: string = '';
  passwort: string = '';
  errorMessage: string = '';

  constructor(private loginService: LoginService, private router: Router) {}

  ngOnInit(): void {
    this.loginService.isLoggedInMitarbeiter$.subscribe((loggedIn) => {
      this.isLoggedIn = loggedIn;
    });
  }

  logout(): void {
    localStorage.removeItem('mitarbeiterId');
    this.isLoggedIn = false;
  }

  login(): void {
    this.loginService
      .loginMitarbeiter(this.username, this.passwort)
      .subscribe((mitarbeiterId) => {
        if (mitarbeiterId.mitarbeiterId !== 'null') {
          localStorage.setItem('mitarbeiterId', mitarbeiterId.mitarbeiterId);
          this.loginService.setLoggedInMitarbeiter(true);
        } else {
          this.errorMessage =
            'Anmeldung fehlgeschlagen. Bitte überprüfe deine Anmeldeinformationen.';
        }
      });
  }

  navigateToInventar(): void {
    if (this.isLoggedIn) {
      this.router.navigate(['/inventar']);
    }
  }

  navigateToProdukt(): void {
    if (this.isLoggedIn) {
      this.router.navigate(['/produkt']);
    }
  }

  navigateToZustellung(): void {
    if (this.isLoggedIn) {
      this.router.navigate(['/zustellung']);
    }
  }
}
