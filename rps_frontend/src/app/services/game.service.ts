import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

interface GameRequest {
  userMove: string;
  computerMove: string;
}

interface GameResult {
  userMove: string;
  computerMove: string;
  result: string;
}

@Injectable({
  providedIn: 'root',
})
export class GameService {
  private apiUrlPlay = 'http://localhost:8080/rps/play';

  constructor(private http: HttpClient) {}

  playGame(uMove: string): Observable<GameResult> {
    return this.http.post<GameResult>(this.apiUrlPlay,{ userMove: uMove});
  }
}
