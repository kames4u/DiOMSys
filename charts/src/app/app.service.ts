import {
  Injectable
} from '@angular/core';
export class Area {
  country: string;
  area: number;
  depth: number;
}

let areas: Area[] = [{
  country: "Russia",
  area: 12,
  depth: 34
}, {
  country: "Canada",
  area: 7,
  depth: 34
}, {
  country: "USA",
  area: 7,
  depth: 34
}, {
  country: "China",
  area: 7,
  depth: 34
}, {
  country: "Brazil",
  area: 6,
  depth: 34
}, {
  country: "Australia",
  area: 5,
  depth: 34
}, {
  country: "India",
  area: 2,
  depth: 34
}, {
  country: "Others",
  area: 55,
  depth: 34
}];

@Injectable()
export class Service {
  getAreas(): Area[] {
    return areas;
  }
}
