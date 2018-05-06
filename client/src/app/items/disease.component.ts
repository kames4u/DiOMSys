import { Component, OnInit } from '@angular/core';
import { DiseaseService, PagerService, Disease } from '../shared';

import { Router, ActivatedRoute } from '@angular/router';
// import 'rxjs/add/operator/map';

@Component({
  selector: 'app-items',
  templateUrl: './disease.component.html',
  styleUrls: ['./disease.component.css']
})
export class DiseaseComponent implements OnInit {
  items: Array<Disease>;
  selectedItem: Disease;

  // pager object
  pager: any = {};

  // paged items
  pagedItems: Disease[];

  constructor(
    private diseasesService: DiseaseService,
    private router: Router,
    private route: ActivatedRoute,
    private pagerService: PagerService
  ) {}

  ngOnInit() {
    this.diseasesService.loadItems()
      .subscribe(data => {
        this.items = data;

        // initialize to page 1
        this.setPage(1);
      });
  }

  resetItem(item: Disease) {
    let emptyItem: Disease = {
        diseaseID: null,
        diseaseName: '',
        occuredCity: '',
        occuredState: '',
        occuredMonth: null,
        occuredYear: null,
        noOfIllness: null,
        noOfHospitalized: null,
        noOfDeath: null,
        dataSource: '',
        category: '',
        status: ''
    };

    this.selectedItem = emptyItem;
  }

  selectItem(item: Disease) {
    this.selectedItem = item;
  }

  saveItem(item: Disease) {
    this.diseasesService.saveItem(item)
      .subscribe(responseItem => {
        if (item.diseaseID) {
          this.replaceItem(responseItem);
        } else {
          this.pushItem(responseItem);
        }
      });

    // Generally, we would want to wait for the result of `itemsService.saveItem`
    // before resetting the current item.
    this.resetItem(item);

    // initialize to page 1
    this.setPage(1);
  }

  replaceItem(item: Disease) {
    this.items = this.items.map(mapItem => {
      return mapItem.diseaseID === item.diseaseID ? item : mapItem;
    });
  }

  pushItem(item: Disease) {
    this.items.push(item);
  }

  deleteItem(item: Disease) {
    this.diseasesService.deleteItem(item)
      .subscribe(() => {
        this.items.splice(this.items.indexOf(item), 1);
      });

    // Generally, we would want to wait for the result of `itemsService.deleteItem`
    // before resetting the current item.
    this.resetItem(item);

    // initialize to page 1
    this.setPage(1);
  }

  setPage(page: number) {
        if (page < 1 || page > this.pager.totalPages) {
            return;
        }

        // get pager object from service
        this.pager = this.pagerService.getPager(this.items.length, page);

        // get current page of items
        this.pagedItems = this.items.slice(this.pager.startIndex, this.pager.endIndex + 1);

        //console.log(this.pager.startIndex);
        //console.log(this.pager.endIndex);
        //console.log(this.pagedItems.length);
        //console.log(this.pagedItems[0]);
  }

}

