import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GameHistoryChartComponent } from './game-history-chart.component';

describe('GameHistoryChartComponent', () => {
  let component: GameHistoryChartComponent;
  let fixture: ComponentFixture<GameHistoryChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GameHistoryChartComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GameHistoryChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
