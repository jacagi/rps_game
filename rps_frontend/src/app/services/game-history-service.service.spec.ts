import { TestBed } from '@angular/core/testing';

import { GameHistoryServiceService } from './game-history-service.service';

describe('GameHistoryServiceService', () => {
  let service: GameHistoryServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GameHistoryServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
