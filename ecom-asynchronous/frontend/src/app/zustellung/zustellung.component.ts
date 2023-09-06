import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ZustellungService } from '../zustellung.service';

@Component({
  selector: 'app-zustellung',
  templateUrl: './zustellung.component.html',
  styleUrls: ['./zustellung.component.scss']
})
export class ZustellungComponent implements OnInit{
  zustellungen: any[] = [];

  constructor(private zustellungService: ZustellungService) { }

  ngOnInit(): void {
    this.zustellungService.getZustellungen().subscribe(data => {
      this.zustellungen = data.map(zustellung => ({
        ...zustellung,
        showDetails: false
      }));
    });
  }

  toggleDetails(zustellung: any): void {
    zustellung.showDetails = !zustellung.showDetails;
  }

  zugestellt(zustellungId : number): void {
    this.zustellungService.zugestellt(zustellungId).subscribe(() => {
      console.log(`Zustellung ${zustellungId} wurde als zugestellt markiert.`);
    });
    setTimeout(() => {
      location.reload();
    }, 100);
  }
}