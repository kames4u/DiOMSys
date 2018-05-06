import { Http, Headers } from '@angular/http';
import { Injectable } from '@angular/core';
import { Disease } from './disease.model';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';

const BASE_URL = 'http://172.22.146.105:8080/DiOMSys/rest/disease/';
let Header = { headers: new Headers({
                            'Content-Type': 'application/json',
                            }
                            ) };
Header.headers.append('Accept', 'application/json')


@Injectable()
export class DiseaseService {
  constructor(private http: Http) {}

  loadItems() {
    return this.http.get(`${BASE_URL}`)
        .map(res => res.json());
  }

  loadItem(id) {
    return this.http.get(`${BASE_URL}${id}`)
      .map(res => res.json());
  }

  saveItem(item: Disease) {
    console.log("into save");
    console.log(item.diseaseID);
    return (item.diseaseID) ? this.updateItem(item) : this.createItem(item);
  }

  createItem(item: Disease) {
    console.log("into create");
    return this.http.post(`${BASE_URL}`, JSON.stringify(item), Header)
      .map(res => res.json());
  }

  updateItem(item: Disease) {
    console.log("into update");
    return this.http.put(`${BASE_URL}${item.diseaseID}`, JSON.stringify(item), Header)
      .map(res => res.json());
  }

  deleteItem(item: Disease) {
    console.log("into delete");
    return this.http.delete(`${BASE_URL}${item.diseaseID}`)
      .map(res => res.json());
  }
}
