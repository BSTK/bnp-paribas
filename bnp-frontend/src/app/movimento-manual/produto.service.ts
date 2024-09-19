import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Produto, ProdutoCosif} from "./movimento-manual.model";
import {environment} from "../../environment/environment";

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {

  constructor(private readonly httpClient: HttpClient) { }

  public produtos(): Observable<Produto[]> {
    return this.httpClient.get<Produto[]>(environment.produtos);
  }

  public cosifs(codigoProduto: string): Observable<ProdutoCosif[]> {
    return this.httpClient.get<ProdutoCosif[]>(`${environment.produtosCosif}/${codigoProduto}`);
  }
}
