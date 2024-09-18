import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'bnp-frontend';
  dados: string[] = [
    'Item A',
    'Item B',
    'Item C'
  ];
}
