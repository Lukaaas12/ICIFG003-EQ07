import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AlumnoStore } from '../services/alumno.store';
import { ConfirmDialogComponent } from '../../../shared/components/confirm-dialog.component';


@Component({
  selector: 'app-alumno-list',
  standalone: true,
  imports: [CommonModule, ConfirmDialogComponent],
  templateUrl: './alumno-list.component.html'
})

export class AlumnoListComponent {
  
  store = inject(AlumnoStore);
  mostrarConfirm = false;
  idAEliminar: number | null = null;

  ngOnInit() {
    this.store.load();
  }

  editar(alumno: any) {
    this.store.select(alumno);
  }

  eliminar(id: number) {
    if (confirm('¿Eliminar alumno?')) {
      this.store.delete(id);
    }
  }

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