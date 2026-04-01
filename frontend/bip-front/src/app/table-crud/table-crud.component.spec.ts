import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableCrudComponent } from './table-crud.component';

describe('TableCrudComponent', () => {
  let component: TableCrudComponent;
  let fixture: ComponentFixture<TableCrudComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TableCrudComponent]
    });
    fixture = TestBed.createComponent(TableCrudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
