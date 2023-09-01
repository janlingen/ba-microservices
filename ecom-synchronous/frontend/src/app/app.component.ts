import { Component, OnInit } from '@angular/core';
import { WarenkorbService } from './warenkorb.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'E-Com';

  constructor(private warenkorb : WarenkorbService){}

  ngOnInit(): void {
  }

}
