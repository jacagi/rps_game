import { Component, OnInit } from '@angular/core';
import { GameHistoryServiceService, GameResult } from '../../services/game-history-service.service';

@Component({
  selector: 'app-game-history-chart',
  standalone: false,
  templateUrl: './game-history-chart.component.html',
  styleUrl: './game-history-chart.component.scss'
})
export class GameHistoryComponent implements OnInit {
  gameHistory: GameResult[] = [];

  constructor(private gameHistoryService: GameHistoryServiceService) {}

  ngOnInit(): void {
    this.fetchGameHistory();
  }

  fetchGameHistory(): void {
    this.gameHistoryService.getGameHistory().subscribe(data => {
      console.log('API Response:', data);
      this.gameHistory = data; 
    });
  }
  getResultClass(result: string): string {
    if (result.toLowerCase() === 'win') return 'win';
    if (result.toLowerCase() === 'lose') return 'lose';
    if (result.toLowerCase() === 'draw') return 'draw';
    return ''; // Default class
  }
}