import {Injectable} from 'angular2/core';
import {Http} from 'angular2/http';
import 'rxjs/add/operator/map';

export class Talk {
  constructor(public id: string, public title: string, public description: string) { }
}

@Injectable()
export class TalkService {
  constructor(private http:Http) {
  }

  getTalks() {
    return this.http.get('http://localhost:8080/Talks/api/talks/')
    	.map(res => res.json());
  }

  getTalk(talkId:string) {
    return this.http.get(`http://localhost:8080/Talks/api/talks/${talkId}`)
    	.map(res => res.json());
  }
}