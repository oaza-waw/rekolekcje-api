import {Component, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {Participant} from '../../models/participant.model';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Parish} from '../../../parish/models/parish.model';
import {AppSelectors} from '../../../core/store/app-selectors';
import {Subject} from 'rxjs/Subject';
import {Parishes} from '../../../parish/store/parish-reducer';
import {Store} from '@ngrx/store';
import {Address} from '../../models/address.model';
import {PersonalData} from '../../models/personal-data.model';
import {HealthReport} from "../../models/heath-report.model";

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

  parishes: Parish[];

  @Output()
  formOutput: EventEmitter<Participant> = new EventEmitter<Participant>();

  form: FormGroup;

  private ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor(private fb: FormBuilder, private store: Store<Parishes.State>) {
  }

  ngOnInit(): void {
    this.store.select(AppSelectors.getParishList)
      .takeUntil(this.ngUnsubscribe)
      .subscribe((parishes: Parish[]) => this.parishes = parishes);
    console.log('personal data: ' + JSON.stringify(this.personalData));
    this.form = this.fb.group({
      firstName: [this.firstName ? this.firstName : '', Validators.required],
      lastName: [this.lastName ? this.lastName : '', Validators.required],
      pesel: [this.pesel ? this.pesel : '', Validators.required],
      address: this.fb.group(this.getAddress()),
      parishId: [this.parishId ? this.parishId : '', Validators.required],
      christeningDate: [this.getChristeningDate()],
      christeningPlace: [this.getChristeningPlace()],
      fatherName: [this.getFatherName()],
      motherName: [this.getMotherName()],
      emergencyContactName: [this.getEmergencyContactName()],
      emergencyContactNumber: [this.getEmergencyContactNumber()],
      healthReport: this.fb.group(this.getHealthReport())
    });
  }

  private getAddress() {
    if (this.address != null) {
      return {
        streetName: [this.address.streetName ? this.address.streetName : ''],
        streetNumber: [this.address.streetNumber ? this.address.streetNumber : ''],
        flatNumber: [this.address.flatNumber ? this.address.flatNumber : ''],
        postalCode: [this.address.postalCode ? this.address.postalCode : ''],
        city: [this.address.city ? this.address.city : ''],
      }
    } else {
      return {streetName: '', streetNumber: '', flatNumber: '', postalCode: '', city: ''}
    }
  }

  private getHealthReport() {
    if (this.healthReport != null) {
      return {
        currentTreatment: [this.healthReport.currentTreatment ? this.healthReport.currentTreatment : ''],
        medications: [this.healthReport.medications ? this.healthReport.medications : ''],
        allergies: [this.healthReport.allergies ? this.healthReport.allergies : ''],
        other: [this.healthReport.other ? this.healthReport.other : ''],
      }
    } else {
      return {
        currentTreatment: '',
        medications: '',
        allergies: '',
        other: '',
      }
    }
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

    const participant = new Participant();
    this.parseParticipantDataFromFrom(participant);
    this.parsePersonalDataFromFormToParticipant(participant);
    this.parseAddressDataFromFormToParticipant(participant);
    this.parseHealthReportDataFromFormToParticipant(participant);
    this.formOutput.emit(participant);
  }

  private parseParticipantDataFromFrom(participant: Participant): void {
    participant.id = this.id;
    participant.firstName = this.form.get('firstName').value;
    participant.lastName = this.form.get('lastName').value;
    participant.pesel = this.form.get('pesel').value;
    participant.parishId = this.form.get('parishId').value;
  }

  private parsePersonalDataFromFormToParticipant(participant: Participant): void {
    const personalData = new PersonalData();
    personalData.christeningDate = this.form.get('christeningDate').value;
    personalData.christeningPlace = this.form.get('christeningPlace').value;
    personalData.fatherName = this.form.get('fatherName').value;
    personalData.motherName = this.form.get('motherName').value;
    personalData.emergencyContactName = this.form.get('emergencyContactName').value;
    personalData.emergencyContactNumber = this.form.get('emergencyContactNumber').value;
    participant.personalData = personalData;
  }

  private parseAddressDataFromFormToParticipant(participant: Participant): void {
    const address = new Address();
    address.streetName = this.form.get('address.streetName').value;
    address.streetNumber = this.form.get('address.streetNumber').value;
    address.flatNumber = this.form.get('address.flatNumber').value;
    address.postalCode = this.form.get('address.postalCode').value;
    address.city = this.form.get('address.city').value;
    participant.address = address;
  }

  private parseHealthReportDataFromFormToParticipant(participant: Participant): void {
    const healthReport = new HealthReport();
    healthReport.currentTreatment = this.form.get('healthReport.currentTreatment').value;
    healthReport.medications = this.form.get('healthReport.medications').value;
    healthReport.allergies = this.form.get('healthReport.allergies').value;
    healthReport.other = this.form.get('healthReport.other').value;
    participant.healthReport = healthReport;
    console.log(JSON.stringify(participant));
  }
}
