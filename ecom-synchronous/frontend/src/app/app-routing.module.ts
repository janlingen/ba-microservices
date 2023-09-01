import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserBereichComponent } from './user-bereich/user-bereich.component';
import { MitarbeiterBereichComponent } from './mitarbeiter-bereich/mitarbeiter-bereich.component';
import { ProduktListeComponent } from './produkt-liste/produkt-liste.component';
import { WarenkorbComponent } from './warenkorb/warenkorb.component';
import { InventarComponent } from './inventar/inventar.component';
import { ProduktComponent } from './produkt/produkt.component';
import { ZustellungComponent } from './zustellung/zustellung.component';

const routes: Routes = [
  { path: 'user-bereich', component: UserBereichComponent },
  { path: 'mitarbeiter-bereich', component: MitarbeiterBereichComponent },
  { path: 'produkte', component: ProduktListeComponent },
  { path: 'warenkorb', component: WarenkorbComponent },
  { path: 'inventar', component: InventarComponent },
  { path: 'produkt', component: ProduktComponent },
  { path: 'zustellung', component: ZustellungComponent },
  {
    path: '**',
    component: ProduktListeComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
