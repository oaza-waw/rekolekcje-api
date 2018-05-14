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

  private ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor(private fb: FormBuilder, private store: Store<Parishes.State>) {
  }

  ngOnInit(): void {
    this.store.select(AppSelectors.getParishList)
      .takeUntil(this.ngUnsubscribe)
      .subscribe((parishes: Parish[]) => this.parishes = parishes);
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
      healthReport: this.fb.group(this.getHealthReport()),
      experience: this.fb.group(this.getExperience())
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
      return { streetName: '', streetNumber: '', flatNumber: '', postalCode: '', city: '' }
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

  private getExperience() {
    if (this.experience != null) {
      return {
        kwcStatus: [this.experience.kwcStatus],
        kwcSince: [this.experience.kwcSince],
        numberOfCommunionDays: [this.experience.numberOfCommunionDays],
        numberOfPrayerRetreats: [this.experience.numberOfPrayerRetreats]
      }
    } else {
      return { kwcStatus: '', kwcSince: '', numberOfCommunionDays: '', numberOfPrayerRetreats: '' }
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

    let participant = new Participant();
    participant.id = this.id;
    participant.firstName = this.form.get('firstName').value;
    participant.lastName = this.form.get('lastName').value;
    participant.pesel = this.form.get('pesel').value;
    participant.parishId = this.form.get('parishId').value;
    participant.personalData = this.getPersonalDataFromForm();
    participant.address = this.getAddressFromForm();
    participant.healthReport = this.getHealthReportFromForm();
    participant.experience = this.getExperienceFromForm();
    this.formOutput.emit(participant);
  }

  private getPersonalDataFromForm(): PersonalData {
    const personalData = new PersonalData();
    personalData.christeningDate = this.form.get('christeningDate').value;
    personalData.christeningPlace = this.form.get('christeningPlace').value;
    personalData.fatherName = this.form.get('fatherName').value;
    personalData.motherName = this.form.get('motherName').value;
    personalData.emergencyContactName = this.form.get('emergencyContactName').value;
    personalData.emergencyContactNumber = this.form.get('emergencyContactNumber').value;
    return personalData;
  }

  private getAddressFromForm(): Address {
    const address = new Address();
    address.streetName = this.form.get('address.streetName').value;
    address.streetNumber = this.form.get('address.streetNumber').value;
    address.flatNumber = this.form.get('address.flatNumber').value;
    address.postalCode = this.form.get('address.postalCode').value;
    address.city = this.form.get('address.city').value;
    return address;
  }

  private getHealthReportFromForm(): HealthReport {
    const healthReport = new HealthReport();
    healthReport.currentTreatment = this.form.get('healthReport.currentTreatment').value;
    healthReport.medications = this.form.get('healthReport.medications').value;
    healthReport.allergies = this.form.get('healthReport.allergies').value;
    healthReport.other = this.form.get('healthReport.other').value;
    return healthReport;
  }

  private getExperienceFromForm(): Experience {
    const experience = new Experience();
    experience.kwcStatus = this.form.get('experience.kwcStatus').value;
    experience.kwcSince = this.form.get('experience.kwcSince').value;
    experience.numberOfCommunionDays = this.form.get('experience.numberOfCommunionDays').value;
    experience.numberOfPrayerRetreats = this.form.get('experience.numberOfPrayerRetreats').value;
    return experience;
  }
}
