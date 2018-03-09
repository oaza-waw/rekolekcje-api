import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  MatButtonModule, MatCardModule, MatDialogModule, MatExpansionModule, MatFormFieldModule,
  MatGridListModule,
  MatIconModule,
  MatInputModule, MatListModule, MatMenuModule, MatPaginatorModule, MatSelectModule, MatSortModule, MatTableModule,
  MatTabsModule, MatToolbarModule
} from '@angular/material';

@NgModule({
  imports: [
    CommonModule,
    MatButtonModule,
    MatDialogModule,
    MatFormFieldModule,
    MatCardModule,
    MatTableModule,
    MatPaginatorModule,
    MatIconModule,
    MatSortModule,
    MatExpansionModule,
    MatListModule,
    MatGridListModule,
    MatInputModule,
    MatToolbarModule,
    MatTabsModule,
    MatSelectModule,
    MatMenuModule,
  ],
  exports: [
    MatButtonModule,
    MatDialogModule,
    MatFormFieldModule,
    MatCardModule,
    MatTableModule,
    MatPaginatorModule,
    MatIconModule,
    MatSortModule,
    MatExpansionModule,
    MatListModule,
    MatGridListModule,
    MatInputModule,
    MatToolbarModule,
    MatTabsModule,
    MatSelectModule,
    MatMenuModule,
  ]
})
export class MaterialModule { }
