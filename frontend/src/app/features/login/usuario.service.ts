import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private http = inject(HttpClient);
  
  // URL exacta de tu controlador en Spring Boot
  private apiUrl = 'http://localhost:8080/api/v1/entities/usuarios';

  // Llama a tu @PostMapping (el de crearUsuario)
  registrar(usuario: any) {
    return this.http.post(this.apiUrl, usuario);
  }

  // Llama a tu @PostMapping("/login")
  login(credenciales: any) {
    return this.http.post(`${this.apiUrl}/login`, credenciales);
  }
}