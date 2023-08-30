import { Component } from '@angular/core';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  username: string = '';
  passwort: string = '';
  errorMessage: string = '';

  constructor(private loginService: LoginService) {}

  login(): void {
    this.loginService
      .loginUser(this.username, this.passwort)
      .subscribe(kundenId => {
        if (kundenId.kundenId !== 'null') {
          localStorage.setItem('kundenId', kundenId.kundenId);
          this.loginService.setLoggedInUser(true)
        } else {
          this.errorMessage =
            'Anmeldung fehlgeschlagen. Bitte überprüfe deine Anmeldeinformationen.';
        }
      });
  }
}
