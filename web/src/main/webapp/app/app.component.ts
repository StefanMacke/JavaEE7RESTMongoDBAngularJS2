import {Component} from 'angular2/core';
import { RouteConfig, ROUTER_DIRECTIVES } from 'angular2/router';
import {ListComponent} from './components/talks.list';
import {DetailsComponent} from './components/talk.details';

@Component({
    selector: 'talks',
    templateUrl: '/app/templates/main.html',
  	directives: [ ROUTER_DIRECTIVES ]
})
@RouteConfig([
  { path: '/talks', component: ListComponent, name: 'Home', useAsDefault: true },
  { path: '/talks/:id', component: DetailsComponent, name: 'Details' }
])
export class AppComponent {
}