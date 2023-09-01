import { HttpClient, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ProduktService } from '../produkt.service';

@Component({
  selector: 'app-produkt',
  templateUrl: './produkt.component.html',
  styleUrls: ['./produkt.component.scss'],
})
export class ProduktComponent implements OnInit {
  produkt: any = {
    name: '',
    marke: '',
    gewicht: 0,
    beschreibung: '',
    preis: 0,
    bildUrl: '',
  };
  errorErstellung = false;

  constructor(private produktService: ProduktService) {}

  ngOnInit(): void {}

  addProdukt(): void {
    this.produktService
      .sendProduktInfo(this.produkt)
      .subscribe((response: HttpResponse<any>) => {
        if (response.status === 201) {
          console.log('Erfolgreich gespeichert.');
        } else {
          this.errorErstellung = true;
          console.error('Fehler beim Speichern.');
        }
      });
  }
}
