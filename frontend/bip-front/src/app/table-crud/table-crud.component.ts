import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DialogCrudComponent } from '../dialog-crud/dialog-crud.component';
import { TransactionService } from '../service/transaction.service';
import { Transaction } from '../model/transaction.model';
import { Transfer } from '../model/transfer.model';

@Component({
  selector: 'app-table-crud',
  templateUrl: './table-crud.component.html',
  styleUrls: ['./table-crud.component.scss']
})
export class TableCrudComponent implements OnInit {
  public dataSource: Transaction[] = []; //aqui vira a response da API REST
  displayedColumns = [
      'id',
      'name',
      'description',
      'value',
      'edit',
      'read',
      'delete',
      'cash',
    ];
  constructor(
    public readonly dialog: MatDialog,
    public readonly service: TransactionService
  ) {}
  public ngOnInit(): void {
    this.getAllTransactions();
  }
  public getAllTransactions(): void {
    this.service.getAllTransactions().subscribe((response: Transaction[]) => {
      this.dataSource = response;
    });
  }
  public openDialogChosen(dataUser: Transaction | undefined, validateCrud: string ): void {
    switch (validateCrud) {
      case 'A':
        this.openDialog(undefined, 'Adicionar');
        break;
      case 'C':
        this.openDialog(dataUser,  'Consultar');
        break;
      case 'E':
        this.openDialog(dataUser, 'Editar');
        break;
      case 'D':
        this.openDialog(dataUser,  'Deletar');
        break;
      case 'T':
        this.openDialog(dataUser,  'Transferência');
        break;
      default:
        break;
    }
    
  }
  public openDialog(dataUser: Transaction | undefined, validateCrud: string): void {
    const dialogRef = this.dialog.open(DialogCrudComponent, {
      data: { dataUser: dataUser, name: validateCrud },
    }); 

    dialogRef.afterClosed().subscribe(result => {
      this.getAllTransactions();
    });
  }
}

