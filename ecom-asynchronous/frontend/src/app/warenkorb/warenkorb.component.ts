import { Component, OnInit } from '@angular/core';
import { WarenkorbService } from '../warenkorb.service';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-warenkorb',
  templateUrl: './warenkorb.component.html',
  styleUrls: ['./warenkorb.component.scss'],
})
export class WarenkorbComponent implements OnInit {
  warenkorb: any = {};
  isLoggedIn = false;

  constructor(
    private warenkorbService: WarenkorbService,
    private loginService: LoginService
  ) {}

  ngOnInit(): void {
    this.warenkorbService.getWarenkorb().subscribe((data) => {
      this.warenkorb = data;
    });
    this.loginService.isLoggedInUser$.subscribe((loggedIn) => {
      this.isLoggedIn = loggedIn;
    });
  }

  calculateGesamtsumme(): number {
    let gesamtsumme = 0;
    if (this.warenkorb && this.warenkorb.produktList) {
      this.warenkorb.produktList.forEach(
        (produkt: { preis: number; anzahl: number }) => {
          gesamtsumme += produkt.preis * produkt.anzahl;
        }
      );
    }
    return gesamtsumme;
  }

  addToWarenkorb(name: string, anzahl: number, preis: number): void {
    this.warenkorbService
      .addProduktToWarenkorb(
        name,
        preis,
        anzahl,
        localStorage.getItem('kundenId')
      )
      .subscribe((response) => {
        console.log(`Produkt in Warenkorb: ${name}, Menge: ${anzahl}`);
      });
  }

  decrementAnzahl(index: number): void {
    if (this.warenkorb.produktList[index].anzahl > 0) {
      this.warenkorb.produktList[index].anzahl--;
      this.addToWarenkorb(
        this.warenkorb.produktList[index].name,
        this.warenkorb.produktList[index].anzahl,
        this.warenkorb.produktList[index].preis
      );
    }
  }

  incrementAnzahl(index: number): void {
    this.warenkorb.produktList[index].anzahl++;
    this.addToWarenkorb(
      this.warenkorb.produktList[index].name,
      this.warenkorb.produktList[index].anzahl,
      this.warenkorb.produktList[index].preis
    );
  }

  bestellungAbschicken(): void {
    this.warenkorbService.bestellungAbschicken(localStorage.getItem('kundenId')).subscribe(response => {
      console.log('Bestellung abgeschickt:', response);
    });

    setTimeout(() => {
      location.reload();
    }, 100);
  }

}
