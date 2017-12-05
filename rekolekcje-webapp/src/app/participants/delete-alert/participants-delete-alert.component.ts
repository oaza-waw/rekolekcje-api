import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { MockParticipantsService } from '../mock-participants.service';
import { ActivatedRoute } from '@angular/router';
import { ModalComponent } from '../../shared/modal/modal.component';

@Component({
  selector: 'participants-delete-alert',
  templateUrl: './participants-delete-alert.component.html',
  styleUrls: ['./participants-delete-alert.component.css']
})
export class ParticipantsDeleteAlertComponent implements OnInit {

  private idToDelete;
  @Output() deleteOneEvent: EventEmitter<number> = new EventEmitter();

  @ViewChild(ModalComponent)
  private modalComponent: ModalComponent;

  constructor(
    private participantsService: MockParticipantsService,
    private route: ActivatedRoute,
  ) { }

  ngOnInit() {
    this.idToDelete = +this.route.snapshot.paramMap.get('id');
  }

  onDeleteConfirm() {
    // this.participantsService.deleteOne(this.idToDelete);
    console.log('delete confirmed');
    this.deleteOneEvent.emit(this.idToDelete);
    this.modalComponent.hide();
  }
}

