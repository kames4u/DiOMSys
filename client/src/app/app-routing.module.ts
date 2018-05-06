import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DiseaseComponent } from './items/disease.component';

const routes: Routes = [
  {path: '', redirectTo: '/diseases', pathMatch: 'full' },
  {path: 'diseases', component: DiseaseComponent
  },
  {path: '**', redirectTo: '/items', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: []
})
export class Ng2RestAppRoutingModule { }
