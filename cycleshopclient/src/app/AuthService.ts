import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './user';
import { Router } from '@angular/router';


@Injectable({
    providedIn: 'root',
})
export class AuthService {
    private apiUrl = 'http://localhost:8080/api/auth/token';
    user: any;
    userRole: string = '';

    constructor(private http: HttpClient, private router: Router) { }

    login(user: User): Observable<any> {
        return this.http.post(`${this.apiUrl}`, user);
    }

    setToken(token: string): void {
        localStorage.setItem('token', token);
    }

    getToken(): string | null {
        return localStorage.getItem('token');
    }

    getHeaders(): HttpHeaders {
        const token = this.getToken();
        return new HttpHeaders({
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`,
        });
    }

    get isLoggedIn(): boolean {
        let authToken = localStorage.getItem('token');
        return authToken !== null ? true : false;
    }

    logout(): void {
        localStorage.removeItem('token');
        this.router.navigate(['login'])
    }

    setUserRole(userRole: string): void {
        this.userRole = userRole;
    }

}
