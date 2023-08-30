import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { ProduktListeComponent } from './produkt-liste/produkt-liste.component';
import { WarenkorbComponent } from './warenkorb/warenkorb.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { UserBereichComponent } from './user-bereich/user-bereich.component';
import { MitarbeiterBereichComponent } from './mitarbeiter-bereich/mitarbeiter-bereich.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { RegistrierenComponent } from './registrieren/registrieren.component';
import { InventarComponent } from './inventar/inventar.component';
import { ProduktComponent } from './produkt/produkt.component';
import { ZustellungComponent } from './zustellung/zustellung.component';
import { InventarService } from './inventar.service';
import { KontoService } from './konto.service';
import { LoginService } from './login.service';
import { ProduktService } from './produkt.service';
import { WarenkorbService } from './warenkorb.service';
import { ZustellungService } from './zustellung.service';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ProduktListeComponent,
    WarenkorbComponent,
    CheckoutComponent,
    UserBereichComponent,
    MitarbeiterBereichComponent,
    LoginComponent,
    RegistrierenComponent,
    InventarComponent,
    ProduktComponent,
    ZustellungComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [
    InventarService,
    KontoService,
    LoginService,
    ProduktService,
    WarenkorbService,
    ZustellungService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
