import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserBereichComponent } from './user-bereich.component';

describe('UserBereichComponent', () => {
  let component: UserBereichComponent;
  let fixture: ComponentFixture<UserBereichComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserBereichComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserBereichComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
