import { NgModule, Component, enableProdMode } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { DxPieChartModule } from 'devextreme-angular';
import { Area, Service } from './app.service';
import { Disease } from './disease.model';
import { DiseaseService } from './disease.service';
import {HttpClientModule} from '@angular/common/http';

if(!/localhost/.test(document.location.host)) {
  enableProdMode();
}
``
@Component({
  selector: 'demo-app',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [Service]
})

export class AppComponent {
  areas: Area[];
  diseases: any[] = [
    {"virusID":1,"virusName":"Norovirus","avgOccur":55},{"virusID":2,"virusName":"Norovirus Genogroup II","avgOccur":55},{"virusID":3,"virusName":"Campylobacter jejuni","avgOccur":55}
    ,{"virusID":4,"virusName":"Norovirus Genogroup I","avgOccur":55},{"virusID":5,"virusName":"Bacillus cereus","avgOccur":55},{"virusID":6,"virusName":"Unknown","avgOccur":55}
    ,{"virusID":7,"virusName":"Salmonella enterica","avgOccur":55},{"virusID":8,"virusName":"Shigella sonnei","avgOccur":55},{"virusID":9,"virusName":"Escherichia coli","avgOccur":55}
    ,{"virusID":10,"virusName":"Shiga toxin-producing","avgOccur":0},{"virusID":11,"virusName":"Norovirus unknown","avgOccur":33},{"virusID":12,"virusName":"Staphylococcus aureus","avgOccur":33}
    ,{"virusID":13,"virusName":"Listeria monocytogenes","avgOccur":33},{"virusID":14,"virusName":"Clostridium perfringens","avgOccur":33},{"virusID":15,"virusName":"Histamine","avgOccur":33}
    //,{"virusID":16,"virusName":"Staphylococcus unknown","avgOccur":33},{"virusID":17,"virusName":"Mycotoxins","avgOccur":33},{"virusID":18,"virusName":"Other - Bacterium","avgOccur":33},{"virusID":19,"virusName":"Enterococcus faecalis","avgOccur":33},{"virusID":20,"virusName":"Campylobacter unknown","avgOccur":0},{"virusID":21,"virusName":"Enterotoxigenic","avgOccur":58},{"virusID":22,"virusName":"Brucella unknown","avgOccur":58},{"virusID":23,"virusName":"Scombroid toxin","avgOccur":58},{"virusID":24,"virusName":"Other - Chemical/Toxin","avgOccur":58},{"virusID":25,"virusName":"Ciguatoxin","avgOccur":58},{"virusID":26,"virusName":"Hepatitis A","avgOccur":58},{"virusID":27,"virusName":"Cyclospora cayetanensis","avgOccur":58},{"virusID":28,"virusName":"Pesticides","avgOccur":58},{"virusID":29,"virusName":"Rotavirus","avgOccur":58},{"virusID":30,"virusName":"Enteroaggregative","avgOccur":0},{"virusID":31,"virusName":"Plant/Herbal toxins","avgOccur":0},{"virusID":32,"virusName":"Clostridium other","avgOccur":0},{"virusID":33,"virusName":"Enterobacter other","avgOccur":0},{"virusID":34,"virusName":"Salmonella unknown","avgOccur":5},{"virusID":35,"virusName":"Clostridium botulinum","avgOccur":0},{"virusID":36,"virusName":"Vibrio parahaemolyticus","avgOccur":0},{"virusID":37,"virusName":"Bacillus unknown","avgOccur":0},{"virusID":38,"virusName":"Giardia intestinalis","avgOccur":0},{"virusID":39,"virusName":"Paralytic shellfish poison","avgOccur":0},{"virusID":40,"virusName":"Campylobacter other","avgOccur":0},{"virusID":41,"virusName":"Vibrio other","avgOccur":0},{"virusID":42,"virusName":"Cryptosporidium parvum","avgOccur":0},{"virusID":43,"virusName":"Other","avgOccur":0},{"virusID":44,"virusName":"Trichinella spiralis","avgOccur":0},{"virusID":45,"virusName":"Campylobacter coli","avgOccur":0},{"virusID":46,"virusName":"Yersinia enterocolitica","avgOccur":0},{"virusID":47,"virusName":"Cryptosporidium unknown","avgOccur":0},{"virusID":48,"virusName":"Vibrio cholerae","avgOccur":0},{"virusID":49,"virusName":"Shigella flexneri","avgOccur":0},{"virusID":50,"virusName":"Trichinella unknown","avgOccur":0},{"virusID":51,"virusName":"Norovirus other","avgOccur":0},{"virusID":52,"virusName":"Streptococcus Group A","avgOccur":0},{"virusID":53,"virusName":"Other - Virus","avgOccur":5},{"virusID":54,"virusName":"Sapovirus","avgOccur":0},{"virusID":55,"virusName":"Astrovirus","avgOccur":0},{"virusID":56,"virusName":"Campylobacter","avgOccur":0},{"virusID":57,"virusName":"Cryptosporidium hominis","avgOccur":0},{"virusID":58,"virusName":"Cryptosporidium","avgOccur":0},{"virusID":59,"virusName":"Shigella unknown","avgOccur":0},{"virusID":60,"virusName":"Norovirus Genogroup IV","avgOccur":0},{"virusID":61,"virusName":"Amnesic shellfish poison","avgOccur":0},{"virusID":62,"virusName":"Sapovirus Genogroup I","avgOccur":0}
    ];

  constructor(service: Service, diseaseService: DiseaseService) {
    this.areas = service.getAreas();

    diseaseService.getAll().subscribe(data => {
      console.log(data);
      // this.diseases = data;
    });

    console.log(this.diseases);

  }

  pointClickHandler(e) {
    this.toggleVisibility(e.target);
  }

  legendClickHandler (e) {
    let arg = e.target,
      item = e.component.getAllSeries()[0].getPointsByArg(arg)[0];

    this.toggleVisibility(item);
  }

  toggleVisibility(item) {
    if(item.isVisible()) {
      item.hide();
    } else {
      item.show();
    }
  }
}

@NgModule({
  imports: [
    BrowserModule,
    DxPieChartModule,
    HttpClientModule
  ],
  declarations: [AppComponent],
  providers: [DiseaseService],
  bootstrap: [AppComponent]
})
export class AppModule { }

platformBrowserDynamic().bootstrapModule(AppModule);
