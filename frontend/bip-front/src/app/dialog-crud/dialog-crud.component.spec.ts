import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogCrudComponent } from './dialog-crud.component';

describe('DialogCrudComponent', () => {
  let component: DialogCrudComponent;
  let fixture: ComponentFixture<DialogCrudComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DialogCrudComponent]
    });
    fixture = TestBed.createComponent(DialogCrudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
