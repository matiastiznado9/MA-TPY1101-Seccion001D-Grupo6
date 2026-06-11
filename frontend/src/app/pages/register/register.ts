import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../core/services/auth';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterModule],
  templateUrl: './register.html',
  styleUrls: ['./register.scss']
})
export class Register {

  nombre = '';
  email = '';
  password = '';
  confirmPassword = '';
  error = '';
  success = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  register() {
    this.error = '';
    this.success = '';

    if (!this.nombre || !this.email || !this.password || !this.confirmPassword) {
      this.error = 'Por favor completa todos los campos';
      return;
    }

    if (this.password !== this.confirmPassword) {
      this.error = 'Las contraseñas no coinciden';
      return;
    }

    this.authService.register(this.nombre, this.email, this.password).subscribe({
      next: () => {
        this.success = 'Cuenta creada correctamente. Serás redirigido al inicio de sesión.';
        setTimeout(() => this.router.navigate(['/']), 1800);
      },
      error: (err: any) => {
        const serverMessage = err?.error;
        this.error = typeof serverMessage === 'string'
          ? `Error al crear la cuenta: ${serverMessage}`
          : 'Error al crear la cuenta. Verifica tus datos e intenta de nuevo.';
      }
    });
  }
}
