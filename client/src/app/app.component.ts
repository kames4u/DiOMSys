import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Disease Outbreak Monitoring System';
  links = {
      diseases: ['/diseases'],
      report: ['/report']
  };
}
