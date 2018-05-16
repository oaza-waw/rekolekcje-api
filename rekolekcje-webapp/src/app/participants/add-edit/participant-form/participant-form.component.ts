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
import { AddressFormComponent } from './address-form/address-form.component';
import { ExperienceFormComponent } from './experience-form/experience-form.component';

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
      firstName: [this.firstName ? this.firstName : '', Validators.required],
      lastName: [this.lastName ? this.lastName : '', Validators.required],
      pesel: [this.pesel ? this.pesel : '', Validators.required],
      address: this.fb.group(AddressFormComponent.buildFormConfig(this.address)),
      parishId: [this.parishId ? this.parishId : ''],
      christeningDate: [this.getChristeningDate()],
      christeningPlace: [this.getChristeningPlace()],
      fatherName: [this.getFatherName()],
      motherName: [this.getMotherName()],
      emergencyContactName: [this.getEmergencyContactName()],
      emergencyContactNumber: [this.getEmergencyContactNumber()],
      healthReport: this.fb.group(HealthReportFormComponent.buildFormConfig(this.healthReport)),
      experience: this.fb.group(ExperienceFormComponent.buildFormConfig(this.experience))
    });
  }

  private getChristeningDate() {
    if (this.personalData == null) return null;
    return this.personalData.christeningDate ? this.personalData.christeningDate : null;
  }

  private getChristeningPlace() {
    if (this.personalData == null) return null;
    return this.personalData.christeningPlace ? this.personalData.christeningPlace : null;
  }

  private getFatherName() {
    if (this.personalData == null) return null;
    return this.personalData.fatherName ? this.personalData.fatherName : null;
  }

  private getMotherName() {
    if (this.personalData == null) return null;
    return this.personalData.motherName ? this.personalData.motherName : null;
  }

  private getEmergencyContactName() {
    if (this.personalData == null) return null;
    return this.personalData.emergencyContactName ? this.personalData.emergencyContactName : null;
  }

  private getEmergencyContactNumber() {
    if (this.personalData == null) return null;
    return this.personalData.emergencyContactNumber ? this.personalData.emergencyContactNumber : null;
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
