import { TestBed } from '@angular/core/testing';

import { ZustellungService } from './zustellung.service';

describe('ZustellungService', () => {
  let service: ZustellungService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ZustellungService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
