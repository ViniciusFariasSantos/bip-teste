/// <reference types="jasmine" />

import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { TransactionService } from './transaction.service';
import { Transaction } from '../model/transaction.model';
import { Transfer } from '../model/transfer.model';

describe('TransactionService', () => {
  let service: TransactionService;
  let httpMock: HttpTestingController;

  const mockTransaction: Transaction = {
    id: 1,
    nome: 'Test Transaction',
    descricao: 'Test Description',
    valor: 100,
    ativo: true,
    version: '1'
  };

  const mockTransactions: Transaction[] = [mockTransaction];

  const mockTransfer: Transfer = {
    fromId: 2,
    toId: 1,
    amount: 50
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [TransactionService]
    });
    service = TestBed.inject(TransactionService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('getAllTransactions', () => {
    it('should return an Observable<Transaction[]>', () => {
      service.getAllTransactions().subscribe(transactions => {
        expect(transactions).toEqual(mockTransactions);
      });

      const req = httpMock.expectOne(service.url);
      expect(req.request.method).toBe('GET');
      req.flush(mockTransactions);
    });
  });

  describe('saveTransaction', () => {
    it('should return an Observable<Transaction>', () => {
      service.saveTransaction(mockTransaction).subscribe(transaction => {
        expect(transaction).toEqual(mockTransaction);
      });

      const req = httpMock.expectOne(service.url);
      expect(req.request.method).toBe('POST');
      expect(req.request.body).toEqual(mockTransaction);
      req.flush(mockTransaction);
    });
  });

  describe('updateTransaction', () => {
    it('should return an Observable<Transaction>', () => {
      service.updateTransaction(mockTransaction).subscribe(transaction => {
        expect(transaction).toEqual(mockTransaction);
      });

      const req = httpMock.expectOne(`${service.url}/${mockTransaction.id}`);
      expect(req.request.method).toBe('PUT');
      expect(req.request.body).toEqual(mockTransaction);
      req.flush(mockTransaction);
    });
  });

  describe('transferValue', () => {
    it('should return an Observable<Transfer>', () => {
      service.transferValue(mockTransfer).subscribe(transfer => {
        expect(transfer).toEqual(mockTransfer);
      });

      const req = httpMock.expectOne(`${service.url}/transfer`);
      expect(req.request.method).toBe('POST');
      expect(req.request.body).toEqual(mockTransfer);
      req.flush(mockTransfer);
    });
  });
});

