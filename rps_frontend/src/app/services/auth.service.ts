import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { throwError,Observable } from 'rxjs';
import { tap, map, catchError } from 'rxjs/operators';

interface LoginRequest {
  username: string;
  password: string;
}

interface AuthResponse {
  token: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/auth/login';
  private apiUrlRegister = 'http://localhost:8080/auth/register';
  private apiUrlLogOut = 'http://localhost:8080/auth/logout';

  constructor(private http: HttpClient) {}

  login(credentials: LoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(this.apiUrl, credentials);
  }

  saveToken(token: string) {
    localStorage.setItem('authToken', token);
  }

  getToken(): string | null {
    return localStorage.getItem('authToken');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  logout() {
    localStorage.removeItem('authToken');
  }

  register(credentials: LoginRequest): Observable<AuthResponse> {
    return this.http.post<any>(this.apiUrlRegister, credentials, { observe: 'response' }).pipe(
      map(response => {
        if (response.status === 200) {
          return response.body;
        }
        throw new Error('Unexpected response status');
      }),
      catchError(err => {
        console.error('Registration Error:', err);
        return throwError(() => new Error(err.error?.message || 'Registration failed'));
      })
    );
  }
}
