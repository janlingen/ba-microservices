import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZustellungComponent } from './zustellung.component';

describe('ZustellungComponent', () => {
  let component: ZustellungComponent;
  let fixture: ComponentFixture<ZustellungComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ZustellungComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ZustellungComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
