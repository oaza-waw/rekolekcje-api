import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Parish} from "../../../parish/models/parish.model";
import {Subject} from "rxjs/Subject";
import {Store} from "@ngrx/store";
import {Parishes} from "../../../parish/store/parish-reducer";
import {AppSelectors} from "../../../core/store/app-selectors";

@Component({
  selector: 'parish-details',
  templateUrl: './parish-details.component.html',
  styleUrls: ['./parish-details.component.css']
})
export class ParishDetailsComponent implements OnInit, OnDestroy {

  @Input() parishId: number;

  parishes: Parish[];

  private ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor(
    private parishStore: Store<Parishes.State>,
  ) { }

  ngOnInit() {
    this.parishStore.select(AppSelectors.getParishList)
      .takeUntil(this.ngUnsubscribe)
      .subscribe((parishes: Parish[]) => this.parishes = parishes);
  }

  ngOnDestroy(): void {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

  getParishName(parishId: number): string {
    let parish = this.findParish(parishId);
    return parish ? parish.name : '';
  }

  getParishAddress(parishId: number): string {
    let parish = this.findParish(parishId);
    return parish ? parish.address : '';
  }

  private findParish(parishId: number): Parish {
    return this.parishes.find(p => p.id === parishId);
  }
}
