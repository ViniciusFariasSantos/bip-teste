import { Component, Inject, OnInit } from '@angular/core';
  import { MAT_DIALOG_DATA } from '@angular/material/dialog';
  import { DataUser } from '../table-crud/table-crud.model';

  @Component({
    selector: 'app-dialog-crud',
    templateUrl: './dialog-crud.component.html',
  styleUrls: ['./dialog-crud.component.scss']
})
export class DialogCrudComponent implements OnInit {

  public user: DataUser = {
    name: '',
    description: '',
    value: 0,
    active: false
  }; 

  constructor ( 
      @Inject (MAT_DIALOG_DATA) public data: { dataUser: DataUser; name: string }

    )
    {}
    ngOnInit(): void {
      if(this.data.dataUser){
        this.user = this.data.dataUser;
      }
    }

}
