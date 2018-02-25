import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject } from 'rxjs/Subject';
import { Parishes } from '../../store/parish-reducer';
import { Store } from '@ngrx/store';
import { Parish } from '../../models/parish.model';
import { ParishSharedActions } from '../../store/parish-actions';
import { AppSelectors } from '../../../core/store/app-selectors';

@Component({
  selector: 'parish-add-edit',
  templateUrl: './parish-add-edit.component.html',
  styleUrls: ['./parish-add-edit.component.css']
})
export class ParishAddEditComponent implements OnInit, OnDestroy {

  editing: boolean;
  parishToEdit: Parish;

  private ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor(private store: Store<Parishes.State>) { }

  ngOnInit() {
    this.store.select(AppSelectors.getSelectedParish)
      .takeUntil(this.ngUnsubscribe)
      .subscribe((parish: Parish) => this.parishToEdit = parish);
  }

  ngOnDestroy(): void {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

  addParish(parish: Parish): void {
    this.store.dispatch(new ParishSharedActions.CreateParish(parish));
  }

  updateParish(parish: Parish): void {
    this.store.dispatch(new ParishSharedActions.UpdateParish(parish));
  }
}
