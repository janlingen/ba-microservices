import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProduktComponent } from './produkt.component';

describe('ProduktComponent', () => {
  let component: ProduktComponent;
  let fixture: ComponentFixture<ProduktComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProduktComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProduktComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
