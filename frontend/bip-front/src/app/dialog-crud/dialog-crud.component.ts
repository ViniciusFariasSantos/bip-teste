import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Transaction } from '../model/transaction.model';
import { Transfer } from '../model/transfer.model';
import { TransactionService } from '../service/transaction.service';

  @Component({
    selector: 'app-dialog-crud',
    templateUrl: './dialog-crud.component.html',
  styleUrls: ['./dialog-crud.component.scss']
})
export class DialogCrudComponent implements OnInit {

  public user: Transaction = {
    nome: '',
    descricao: '',
    valor: 0,
    ativo: true,
    version: '',
    id: 0 
  }; 
  public transfer: Transfer = {
    fromId: 0,
    toId: 0,
    amount: 0
  };
  booleanName: boolean = false;
  booleanTransfer: boolean = false;

  constructor ( 
      @Inject (MAT_DIALOG_DATA) public data: { dataUser: Transaction; name: string },
      private readonly transactionService: TransactionService,
    )
    {}
    ngOnInit(): void {
      if (this.data.name === 'Consultar' || this.data.name === 'Deletar') {
        this.booleanName = true;
      } else if (this.data.name ===   'Transferência') {
        this.booleanTransfer = true;
      }
      if(this.data.dataUser){
        this.user = this.data.dataUser;
        this.transfer.toId = this.user.id;
        this.transfer.amount = this.user.valor;
      }
    }

    public clickButton(): void {
      switch (this.data.name) {
        case 'Adicionar':
          this.addTransaction();
          break;
        case 'Editar':
          this.editTransaction();
          break;
        case 'Deletar':
          this.deleteTransaction();
          break;
        case 'Transferência':
          this.transferValue();
          break;
      } 
    } 
    public addTransaction(): void {
      this.transactionService.saveTransaction(this.user).subscribe( response => {

      }); 
    }
    public editTransaction(): void {
      this.transactionService.updateTransaction(this.user).subscribe( response => {
      } );
    }
    public deleteTransaction(): void {
      this.transactionService.deleteTransaction(this.user.id).subscribe( response => {
      });
    }
    public transferValue(): void {
      this.transactionService.transferValue(this.transfer).subscribe( response => {
      });
    }

}
