import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-landing',
  standalone: true,
  imports: [RouterLink], // Esto permite que el botón viaje al login
  templateUrl: './landing.component.html'
})
export class LandingComponent { }