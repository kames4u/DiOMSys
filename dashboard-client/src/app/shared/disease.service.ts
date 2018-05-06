import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Disease } from './disease.model';


@Injectable()
export class DiseaseService {

  public BASE_URL = 'http://172.22.146.105:8080/DiOMSys/rest/disease';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any> {
    console.log(this.BASE_URL);
    return this.http.get(this.BASE_URL);
  }

  get(id: string) {
    return this.http.get(this.BASE_URL + '/' + id);
  }

  save(item: Disease, flag: String) {
    console.log("into save");
    console.log(item.diseaseID);
    console.log(flag);

    return (flag == 'edit') ? this.updateItem(item) : this.createItem(item);
  }

  createItem(disease: Disease) {
    console.log("into create");
    return this.http.post(this.BASE_URL, disease);
  }

  updateItem(disease: Disease) {
    console.log("into update");
    return this.http.put(this.BASE_URL + '/' + disease.diseaseID, disease);
  }

  remove(id: string) {
    console.log("into delete");
    console.log(id);
    return this.http.delete(this.BASE_URL + '/' + id);
  }
}
