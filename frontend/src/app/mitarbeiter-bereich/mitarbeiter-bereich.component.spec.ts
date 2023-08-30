import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MitarbeiterBereichComponent } from './mitarbeiter-bereich.component';

describe('MitarbeiterBereichComponent', () => {
  let component: MitarbeiterBereichComponent;
  let fixture: ComponentFixture<MitarbeiterBereichComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MitarbeiterBereichComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MitarbeiterBereichComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
