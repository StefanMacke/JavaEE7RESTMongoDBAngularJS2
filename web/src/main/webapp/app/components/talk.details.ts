import {Component} from 'angular2/core';
import {RouteParams} from 'angular2/router';
import {FormFieldComponent} from './form.field';
import {TalkService, Talk} from '../services/talks.service';

@Component({
  selector: 'talk-details',
  directives: [ FormFieldComponent ],
  templateUrl: '/app/templates/talk.details.html'
})
export class DetailsComponent {
  constructor(private service:TalkService, private routeParams: RouteParams) {
	let talkId = this.routeParams.get('id');
	service.getTalk(talkId).subscribe(
      talk => this.talk = talk
	);
  }
}