import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Disease } from '../../shared';

@Component({
  selector: 'app-item-detail',
  templateUrl: './disease-detail.component.html',
  styleUrls: ['./disease-detail.component.css']
})
export class DiseaseDetailComponent {
  originalName: string;
  selectedItem: Disease;
  @Output() saved = new EventEmitter();
  @Output() cancelled = new EventEmitter();

  @Input() set item(value: Disease){
    if (value) { this.originalName = value.diseaseName; }
    this.selectedItem = Object.assign({}, value);
  }
}
