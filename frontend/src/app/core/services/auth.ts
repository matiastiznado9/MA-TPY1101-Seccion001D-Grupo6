import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private authUrl = 'http://localhost:8080/api/auth';
  private userUrl = 'http://localhost:8080/api/usuarios';

  constructor(private http: HttpClient) {}

  login(email: string, password: string) {
    return this.http.post(`${this.authUrl}/login`, {
      email,
      password
    });
  }

  register(nombre: string, email: string, password: string) {
    return this.http.post(this.userUrl, {
      nombre,
      email,
      password
    });
  }
}