import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Owner } from './owner';
import { environment } from 'src/environments/environment';;

@Injectable({
  providedIn: 'root'
})
export class OwnersService {
  constructor(private http: HttpClient) { }

  public getOwners(): Observable<Owner[]> {
    return this.http.get<Owner[]>(`${environment.apiUrl}/owners`);
  }
}
