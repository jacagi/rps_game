import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { switchMap } from 'rxjs/operators';

export interface GameResult {
  userMove: string;
  computerMove: string;
  result: string;
}

@Injectable({
  providedIn: 'root'
})
export class GameHistoryServiceService {

  private apiUrl = 'http://localhost:8080/rps/history';

  private refreshGameHistory$ = new BehaviorSubject<void>(undefined);

  constructor(private http: HttpClient) {}

  getGameHistory(): Observable<GameResult[]> {
    return this.refreshGameHistory$.pipe(
      switchMap(() => this.http.post<GameResult[]>(this.apiUrl,{ username: "Test", gamesNum: 10})
    ));
  }
  refreshHistory(): void {
    this.refreshGameHistory$.next();
  }
}
