import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { DataTableResource } from 'angular5-data-table';
import { DiseaseService } from './shared/disease.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Disease Outbreak Monitoring System';
  userForm: FormGroup;
  @ViewChild('modalClose') modalClose: ElementRef;
  // Static data, you can change as per your need
  disease: any[] = [
    {
      "diseaseID":1,"diseaseName":"loading..",
      "occuredYear": "loading..","occuredMonth":"loading..","occuredCity":"loading..",
      "occuredState":"loading..","status":"loading..","noOfIllness":"loading..",
      "noOfDeath":"loading..","noOfHospitalized":"loading..","dataSource":"loading..","category":"loading.."
    }
  ];

  itemResource = new DataTableResource(this.disease);
  items = [];
  itemCount = 0;
  params = { offset: 0, limit: 10 }; // Static can be changed as per your need
  formFlag = 'Add';

  constructor(private diseaseService: DiseaseService) {
    this.itemResource.count().then(count => this.itemCount = count);
    this.reloadItems(this.params);
  }

  reloadItems(params) {
    this.itemResource.query(params).then(items => this.items = items);
  }

  // special properties:
  rowClick(rowEvent) {
    console.log('Clicked: ' + rowEvent.row.item.diseaseName);
  }

  rowDoubleClick(rowEvent) {
    alert(
      'Disease Name  :  ' + rowEvent.row.item.diseaseName + '       ' +
      'Disease Year  :  ' + rowEvent.row.item.occuredYear
    );
  }

  rowTooltip(item) { return item.diseaseName; }

  // Init method
  ngOnInit() {
    this.userForm = new FormGroup({
      'diseaseID': new FormControl(null),
      'diseaseName': new FormControl(null, Validators.required),
      'occuredYear': new FormControl(null, Validators.required),
      'occuredMonth': new FormControl(null, Validators.required),
      'occuredCity': new FormControl(null, Validators.required),
      'occuredState': new FormControl(null, Validators.required),
      'status': new FormControl(null, Validators.required),
      'noOfIllness': new FormControl(null, Validators.required),
      'noOfDeath': new FormControl(null, Validators.required),
      'noOfHospitalized': new FormControl(null, Validators.required),
      'dataSource': new FormControl(null, Validators.required),
      'category': new FormControl(null, Validators.required),
    });

    this.diseaseService.getAll().subscribe(data => {
      this.disease = data;

      console.log('init');
      console.log(this.disease);

      this.itemResource = new DataTableResource(this.disease);
      this.itemResource.count().then(count => this.itemCount = count);
      this.reloadItems(this.params);

    });

  }

  initUser() {
    // User form reset
    this.userForm.reset();
    this.formFlag = 'Add';
  }

  // Save user's data
  saveUser() {
    console.log(this.formFlag);
    if (this.formFlag === 'Add') {
      this.userForm.value.diseaseID = this.disease.length + 1;
      this.disease.unshift(this.userForm.value);
      this.diseaseService.save(this.userForm.value, 'add').subscribe(result => {
      }, error => console.error(error));

    } else {
      console.log(this.userForm.value)
      const index = this.disease.findIndex(x => x.diseaseID === this.userForm.value.diseaseID);

      if (index !== -1) {
        this.disease[index] = this.userForm.value;
      }

      this.diseaseService.save(this.userForm.value, 'edit').subscribe(result => {
      }, error => console.error(error));

    }
    this.reloadTableManually();
    // Close modal
    this.modalClose.nativeElement.click();
    // User form reset
    this.userForm.reset();
  }
  // Get data while edit
  getData(item) {
    this.userForm.patchValue(item);
    this.formFlag = 'edit';
  }
  // Delete user's data
  delData(item) {
    this.disease.splice(this.disease.indexOf(item), 1);
    this.reloadTableManually();

    this.diseaseService.remove(item.diseaseID).subscribe(result => {
    }, error => console.error(error));
  }
  // Reload table manually after add/edit
  reloadTableManually() {
    this.reloadItems(this.params);
    this.itemResource.count().then(count => this.itemCount = count);
  }
}
