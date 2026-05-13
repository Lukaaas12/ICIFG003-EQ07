import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AlumnoStore } from '../services/alumno.store';

@Component({
  selector: 'app-alumno-list',
  standalone: true,
  // Quitamos ConfirmDialogComponent de aquí para evitar el Warning NG8113
  imports: [CommonModule], 
  templateUrl: './alumno-list.component.html'
})
export class AlumnoListComponent implements OnInit {
  store = inject(AlumnoStore);
  
  // Variables para tu confirm personalizado (si decides usarlo en el HTML)
  mostrarConfirm = false;
  idAEliminar: number | null = null;

  ngOnInit() {
    this.store.load();
  }

  editar(alumno: any) {
    this.store.select(alumno);
  }

  // Opción 1: Confirmación rápida del navegador
  eliminar(id: number) {
    if (confirm('¿Desea cerrar este protocolo de desregulación?')) {
      this.store.delete(id);
    }
  }

  // Opción 2: Tu lógica para un modal personalizado
  abrirConfirm(id: number) {
    this.idAEliminar = id;
    this.mostrarConfirm = true;
  }

  confirmarEliminacion() {
    if (this.idAEliminar !== null) {
      this.store.delete(this.idAEliminar);
    }
    this.cerrarConfirm();
  }

  cerrarConfirm() {
    this.mostrarConfirm = false;
    this.idAEliminar = null;
  }
}