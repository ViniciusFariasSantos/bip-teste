import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DialogCrudComponent } from '../dialog-crud/dialog-crud.component';
import { DataUser } from './table-crud.model';

@Component({
  selector: 'app-table-crud',
  templateUrl: './table-crud.component.html',
  styleUrls: ['./table-crud.component.scss']
})
export class TableCrudComponent {
  public ELEMENT_DATA: DataUser[] = [
    {description: '1', name: 'Hydrogen', value: 1.0079, active: true},
    {description: '2', name: 'Helium', value: 4.0026, active: true},
    {description: '3', name: 'Lithium', value: 6.941, active: true},
    {description: '4', name: 'Beryllium', value: 9.0122, active: true},
    {description: '5', name: 'Boron', value: 10.811, active: true},
    {description: '6', name: 'Carbon', value: 12.0107, active: true},
    {description: '7', name: 'Nitrogen', value: 14.0067, active : true},
    {description: '8', name: 'Oxygen', value: 15.9994, active: true},
    {description: '9', name: 'Fluorine', value: 18.9984, active: true},
    {description: '10', name: 'Neon', value: 20.1797, active: true},
  ];
  constructor(public dialog: MatDialog) {}
  displayedColumns = [
      'name',
      'description',
      'value',
      'active',
      'edit',
      'read',
      'delete',
    ];
  dataSource = this.ELEMENT_DATA; //aqui vira a response da API REST
  public openDialogChosen(dataUser: DataUser | undefined, validateCrud: string ): void {
    switch (validateCrud) {
      case 'A':
        this.openDialog(undefined, 'Adicionar');
        break;
      case 'C':
        this.openDialog(dataUser, 'Consultar');
        break;
      case 'E':
        this.openDialog(dataUser, 'Editar');
        break;
      case 'D':
        this.openDialog(dataUser, 'Deletar');
        break;
    }
    
  }
  public openDialog(dataUser: DataUser | undefined, validateCrud: string): void {
    const dialogRef = this.dialog.open(DialogCrudComponent, {
      data: { dataUser: dataUser, name: validateCrud },
    }); 

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }
}

