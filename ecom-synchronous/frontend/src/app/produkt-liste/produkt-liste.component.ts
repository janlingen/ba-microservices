import { Component, OnInit } from '@angular/core';
import { ProduktService } from '../produkt.service';
import { LoginService } from '../login.service';
import { HttpClient } from '@angular/common/http';
import { WarenkorbService } from '../warenkorb.service';

@Component({
  selector: 'app-produkt-liste',
  templateUrl: './produkt-liste.component.html',
  styleUrls: ['./produkt-liste.component.scss'],
})
export class ProduktListeComponent implements OnInit {
  produkte: any[] = [];
  mengen: number[] = [];
  isLoggedIn: boolean = false;

  constructor(
    private produktService: ProduktService,
    private loginService: LoginService,
    private http: HttpClient,
    private warenkorbService: WarenkorbService
  ) {}

  ngOnInit(): void {
    this.produktService.getProdukte().subscribe((data) => {
      this.produkte = data;
      this.mengen = new Array(this.produkte.length).fill(1);
    });
    this.loginService.isLoggedInUser$.subscribe((loggedIn) => {
      this.isLoggedIn = loggedIn;
    });
  }

  incrementMenge(index: number): void {
    this.mengen[index]++;
  }

  decrementMenge(index: number): void {
    if (this.mengen[index] > 1) {
      this.mengen[index]--;
    }
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
}
