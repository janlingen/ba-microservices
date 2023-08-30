import { Component } from '@angular/core';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-registrieren',
  templateUrl: './registrieren.component.html',
  styleUrls: ['./registrieren.component.scss'],
})
export class RegistrierenComponent {
  userData: any = {
    username: '',
    passwort: '',
    kundenId: '',
    vorname: '',
    nachname: '',
    postleitzahl: '',
    stadt: '',
    strasse: '',
    hausnummer: '',
  };
  errorMessage: string = '';

  constructor(private loginService: LoginService) {}

  register(): void {
    this.loginService.registrierenUser(this.userData).subscribe(kundenId => {
      if (kundenId.kundenId !== 'null') {
        localStorage.setItem('kundenId', kundenId.kundenId);
        this.loginService.setLoggedInUser(true)
      } else {
        this.errorMessage =
          'Registrierung fehlgeschlagen. Bitte überprüfe deine Registrierungsinformationen.';
      }
    });
  }
}
