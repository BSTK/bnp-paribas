import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {MovimentoManual, MovimentoManualRequest} from "./movimento-manual.model";
import {environment} from "../../environment/environment";

@Injectable({
  providedIn: 'root'
})
export class MovimentoManualService {

  constructor(private readonly httpClient: HttpClient) { }

  public movimentos(): Observable<MovimentoManual[]> {
    return this.httpClient.get<MovimentoManual[]>(environment.movimentos);
  }

  public incluirMovimento(movimento: MovimentoManualRequest): Observable<void> {
    return this.httpClient.post<void>(environment.movimentos, movimento);
  }
}
