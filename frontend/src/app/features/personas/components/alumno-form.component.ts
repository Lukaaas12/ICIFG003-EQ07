import { Component, inject, effect, ChangeDetectorRef } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AlumnoStore } from '../services/alumno.store';

@Component({
  selector: 'app-alumno-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './alumno-form.component.html'
})
export class AlumnoFormComponent {
  private fb = inject(FormBuilder);
  private _store = inject(AlumnoStore);
  private cd = inject(ChangeDetectorRef); // Inyectado para forzar la detección si es necesario

  get store() { return this._store; }

  form = this.fb.group({
    id: [0],
    nombre: ['', Validators.required],
    curso: ['', Validators.required],
    protocoloId: ['', Validators.required] // <--- CAMBIADO: Coincide con el HTML
  });

  constructor() {
    effect(() => {
      const alumno = this.store.selected();
      if (alumno) {
        // Usamos patchValue de forma segura
        this.form.patchValue({
          id: alumno.id,
          nombre: alumno.nombre,
          curso: alumno.curso,
          protocoloId: alumno.protocoloId // Mapeas el dato de la DB al nombre del control
        });
        
        // Esto le dice a Angular que revise los cambios en el siguiente ciclo
        // evitando el error ExpressionChangedAfterItHasBeenChecked
        this.cd.detectChanges(); 
      }
    });
  }

  guardar() {
    if (this.form.invalid) return;
    
    // Obtenemos los valores y nos aseguramos de que el nombre sea el que espera el backend
    const rawValue = this.form.value;
    const alumnoParaGuardar = {
      ...rawValue,
      protocolo_id: rawValue.protocoloId // Mapeas de vuelta antes de enviar al store
    };

    if (alumnoParaGuardar.id) {
      this.store.update(alumnoParaGuardar as any);
    } else {
      this.store.add(alumnoParaGuardar as any);
    }
    
    this.limpiar();
  }

  cancelar() {
    this.store.clearSelection();
    this.limpiar();
  }

  private limpiar() {
    this.form.reset({ id: 0, nombre: '', curso: '', protocoloId: '' });
  }
}