import { Component, OnInit } from '@angular/core';
import { InventarService } from '../inventar.service';

@Component({
  selector: 'app-inventar',
  templateUrl: './inventar.component.html',
  styleUrls: ['./inventar.component.scss']
})
export class InventarComponent implements OnInit {
  inventar: any[] = [];

  constructor(private inventarService: InventarService) { }

  ngOnInit(): void {
    this.inventarService.getInventar().subscribe(data => {
      this.inventar = data;
    });
  }

  erhoeheAnzahl(index: number): void {
    this.inventar[index].anzahl++;
    this.inventarService.erhoeheAnzahl(this.inventar[index]).subscribe(response => {});
  }

  verringereAnzahl(index: number): void {
    if (this.inventar[index].anzahl > 0) {
      this.inventar[index].anzahl--;
      this.inventarService.verringereAnzahl(this.inventar[index]).subscribe(response => {});
    }
  }
}