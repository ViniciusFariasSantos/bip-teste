import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Transaction } from '../model/transaction.model';
import { Transfer } from '../model/transfer.model';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  public url: string =  "http://localhost:8081/beneficios"
  constructor(
    private readonly http: HttpClient
  ) { }

  public getAllTransactions() : Observable<Transaction[]>   {
    return this.http.get<Transaction[]>(this.url);
  }
  public saveTransaction(transaction: Transaction) : Observable<Transaction> {
    return this.http.post<Transaction>(this.url, transaction);
  }

  public updateTransaction(transaction: Transaction) : Observable<Transaction> {
    return this.http.put<Transaction>(`${this.url}/${transaction.id}`, transaction);
  }

  public deleteTransaction(id: number) : Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }

  public transferValue(transferData: Transfer) : Observable<Transfer> {
    return this.http.post<Transfer>(`${this.url}/transfer`, transferData);
  }
}
