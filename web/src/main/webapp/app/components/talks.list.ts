import {Component} from 'angular2/core';
import {Router} from 'angular2/router';
import {TalkService, Talk} from '../services/talks.service';

@Component({
  selector: 'talk-list',
  template: `
    <h1>Talks</h1>
    <ul>
      <li *ngFor="#talk of (talks | async)">
        <a href="#" (click)="selectTalk(talk)">
          {{talk.title}}
        </a>
      </li>
    </ul>
  `
})
export class ListComponent {
  constructor(private service:TalkService, private router:Router) {
    this.talks = service.getTalks();
  }

  selectTalk(talk:Talk) {
    this.router.navigate([ 'Details', { id: talk.id }]);
    return false;
  }
}