import { Component, OnDestroy, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Parish } from '../shared/models/parish.model';
import { Subject } from 'rxjs/Subject';
import { Parishes } from '../core/store/parish/parish-reducer';
import { Store } from '@ngrx/store';
import { ParishSharedActions } from '../shared/store-shared/parish/parish-actions';
import { AppSelectors } from '../core/store/app-selectors';

@Component({
  selector: 'parish-root',
  templateUrl: './parish.component.html',
  styleUrls: ['./parish.component.css']
})
export class ParishComponent implements OnInit, OnDestroy {

  parishes: Observable<Parish[]>;

  private ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor(private store: Store<Parishes.State>) {
  }

  ngOnInit() {
    this.store.dispatch(new ParishSharedActions.LoadParishList());
    this.parishes = this.store.select(AppSelectors.getParishList);
  }

  ngOnDestroy(): void {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

  onAddParish(parish: Parish): void {
    this.store.dispatch(new ParishSharedActions.CreateParish(parish))
  }

  onDeleteParish(id: number): void {
    this.store.dispatch(new ParishSharedActions.DeleteParish(id))
  }

  onEditParish(parish: Parish): void {
    this.store.dispatch(new ParishSharedActions.UpdateParish(parish))
  }
}
