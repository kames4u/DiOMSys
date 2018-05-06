import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Disease } from '../../shared';

@Component({
  selector: 'app-items-list',
  templateUrl: './disease-list.component.html',
  styleUrls: ['./disease-list.component.css']
})
export class DiseaseListComponent {
  @Input() items: Disease[];
  @Output() selected = new EventEmitter();
  @Output() deleted = new EventEmitter();
}
