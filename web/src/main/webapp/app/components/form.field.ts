import {Component,Input,OnInit,Query,QueryList,ElementRef} from 'angular2/core';
import {NgClass,Control} from 'angular2/common';

@Component({
  selector: 'field',
  template: `
    <div class="form-group form-group-sm">
      <label for="for"
         class="col-sm-3 col-md-3 control-label"
         style="padding-right: 5px;">{{label}}</label>
      <div #content class="col-sm-8 col-md-8"
           style="padding-left: 5px">
        <ng-content ></ng-content>
      </div>
    </div>
  `
})
export class FormFieldComponent {
  @Input()
  label: string;

  @Input()
  state: Control;
}