import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject } from 'rxjs/Subject';
import { Parishes } from '../../../core/store/parish/parish-reducer';
import { Store } from '@ngrx/store';
import { Parish } from '../../../shared/models/parish.model';
import { ParishSharedActions } from '../../../shared/store-shared/parish/parish-actions';

@Component({
  selector: 'parish-add-edit',
  templateUrl: './parish-add-edit.component.html',
  styleUrls: ['./parish-add-edit.component.css']
})
export class ParishAddEditComponent implements OnInit, OnDestroy {

  private ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor(private store: Store<Parishes.State>) { }

  ngOnInit() {
  }

  ngOnDestroy(): void {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

  addParish(parish: Parish): void {
    this.store.dispatch(new ParishSharedActions.CreateParish(parish));
  }
}
