import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Parish } from '../shared/models/parish.model';
import { Subject } from 'rxjs/Subject';

@Component({
  selector: 'app-parish',
  templateUrl: './parish.component.html',
  styleUrls: ['./parish.component.css']
})
export class ParishComponent implements OnInit {

  parishes: Observable<Parish[]>;

  private ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor() { }

  ngOnInit() {
  }

}
