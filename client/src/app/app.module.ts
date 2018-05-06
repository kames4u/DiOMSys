import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { Ng2RestAppRoutingModule } from './app-routing.module';

import { DiseaseService, PagerService } from './shared';

import { AppComponent } from './app.component';
import { DiseaseComponent } from './items/disease.component';
import { DiseaseListComponent } from './items/disease-list/disease-list.component';
import { DiseaseDetailComponent } from './items/disease-detail/disease-detail.component';


@NgModule({
  declarations: [
    AppComponent,
    DiseaseComponent,
    DiseaseListComponent,
    DiseaseDetailComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    Ng2RestAppRoutingModule
  ],
  providers: [DiseaseService, PagerService],
  bootstrap: [AppComponent]
})
export class AppModule { }
