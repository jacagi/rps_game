import { Component } from '@angular/core';
import { GameService } from '../../services/game.service';
import { faHandRock, faHandPaper, faHandScissors } from '@fortawesome/free-solid-svg-icons';
import { GameHistoryServiceService } from '../../services/game-history-service.service';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-game',
  standalone: false,
  templateUrl: './game.component.html',
  styleUrl: './game.component.scss'
})
export class GameComponent {
  userMove: string = '';
  computerMove: string = '';
  result: string = '';
  faRock = faHandRock;
  faPaper = faHandPaper;
  faScissors = faHandScissors;
  constructor(private gameService: GameService, private gameHistoryService: GameHistoryServiceService, private router: Router, private authService: AuthService) {}
  play(move: string) {
    this.userMove = move;
    this.gameService.playGame(this.userMove).subscribe((gameResult) => {
      this.computerMove = gameResult.computerMove;
      this.result = gameResult.result;
      this.gameHistoryService.refreshHistory();
    });
  }
  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
