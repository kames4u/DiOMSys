import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class DiseaseService {

  public BASE_URL = 'http://localhost:8080/DioMSys/rest/virus';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any> {
    console.log(this.BASE_URL);
    return this.http.get(this.BASE_URL);
  }

}
