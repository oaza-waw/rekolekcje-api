import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { Participant } from '../../models/participant.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Parish } from '../../../parish/models/parish.model';
import { AppSelectors } from '../../../core/store/app-selectors';
import { Subject } from 'rxjs/Subject';
import { Parishes } from '../../../parish/store/parish-reducer';
import { Store } from '@ngrx/store';
import { Address } from '../../models/address.model';
import { PersonalData } from '../../models/personal-data.model';
import { HealthReport } from '../../models/heath-report.model';
import { Experience } from '../../models/experience.model';
import { HealthReportFormComponent } from './health-report-form/health-report-form.component';
import { ExperienceFormComponent } from './experience-form/experience-form.component';
import { PersonalDataFormComponent } from './personal-data-form/personal-data-form.component';

@Component({
  selector: 'participiant-form',
  templateUrl: './participant-form.component.html',
  styleUrls: ['./participant-form.component.css'],
})
export class ParticipantFormComponent implements OnInit, OnDestroy {

  @Input() firstName: string;
  @Input() id: number;
  @Input() lastName: string;
  @Input() pesel: string;
  @Input() parishId: string;
  @Input() address: Address;
  @Input() personalData: PersonalData;
  @Input() healthReport: HealthReport;
  @Input() experience: Experience;

  parishes: Parish[];

  @Output()
  formOutput: EventEmitter<Participant> = new EventEmitter<Participant>();

  form: FormGroup;

  currentStep = 0;

  private ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor(private fb: FormBuilder, private store: Store<Parishes.State>) { }

  ngOnInit(): void {
    this.store.select(AppSelectors.getParishList)
      .takeUntil(this.ngUnsubscribe)
      .subscribe((parishes: Parish[]) => this.parishes = parishes);
    this.form = this.fb.group({
      personalDataGroup: PersonalDataFormComponent.buildFormConfig(this.firstName, this.lastName, this.pesel, this.parishId, this.personalData, this.address),
      healthReport: this.fb.group(HealthReportFormComponent.buildFormConfig(this.healthReport)),
      experience: ExperienceFormComponent.buildFormConfig(this.experience)
    });
  }

  ngOnDestroy(): void {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

  cancel(): void {
    this.formOutput.emit();
  }

  submit(): void {
    if (this.form.invalid) {
      this.form.markAsDirty();
      return;
    }

    const participant = Participant.parseForm(this.id, this.form);
    this.formOutput.emit(participant);
  }

  setCurrentStep(index: number) {
    this.currentStep = index;
  }

  nextStep() {
    this.currentStep++;
  }

  previousStep() {
    this.currentStep--;
  }
}
