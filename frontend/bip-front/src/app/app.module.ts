import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatIconModule} from  '@angular/material/icon' ;
import {MatTableModule} from  '@angular/material/table' ;
import {MatTooltipModule} from '@angular/material/tooltip';
import { DialogCrudComponent } from './dialog-crud/dialog-crud.component';
import { TableCrudComponent } from './table-crud/table-crud.component';
import { MatDialogModule} from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';



@NgModule({
  declarations: [
    AppComponent,
    DialogCrudComponent,
    TableCrudComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatTableModule, 
    MatIconModule,
    MatTooltipModule,
    MatButtonModule, 
    MatDialogModule,
    FormsModule, 
    MatFormFieldModule, 
    MatInputModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
