import {Component, OnInit} from '@angular/core';
import {ProdutoService} from "./produto.service";
import {MovimentoManualService} from "./movimento-manual.service";
import {
  MovimentoManual,
  MovimentoManualRequest,
  Produto,
  ProdutoCosif
} from "./movimento-manual.model";
import {forkJoin} from "rxjs";

@Component({
  selector: 'app-movimento-manual',
  templateUrl: './movimento-manual.component.html'
})
export class MovimentoManualComponent implements OnInit {

  desabilitarCampos = true;
  desabilitarCamposCosif = true;

  produtos: Produto[] = [];
  cosifs: ProdutoCosif[] = [];
  movimentos: MovimentoManual[] = [];
  movimentoManual: MovimentoManualRequest = {} as MovimentoManualRequest;

  constructor(private readonly produtoService: ProdutoService,
              private readonly movimentoManualService: MovimentoManualService) {
  }

  ngOnInit(): void {
    this.movimentoManual.codigoCosif = '0000';
    this.movimentoManual.codigoProduto = '0000';

    const $produtos = this.produtoService.produtos();
    const $movimentos = this.movimentoManualService.movimentos();

    forkJoin([$produtos, $movimentos])
    .subscribe(([produtosResponse, movimentosresponse]) => {
      if (produtosResponse) { this.produtos = produtosResponse; }
      if (movimentosresponse) { this.movimentos = movimentosresponse; }
    });
  }

  onNovo(): void {
    this.desabilitarCampos = false;
  }

  onLimpar(): void {
    this.resetarFormulario();
  }

  onIncluir(): void {
    this.movimentoManualService
      .incluirMovimento(this.movimentoManual)
      .subscribe(() => {
        this.resetarFormulario();

        this.movimentoManualService
          .movimentos()
          .subscribe((movimentosresponse) => {
            if (movimentosresponse) {
              this.movimentos = movimentosresponse;
            }
          });
      });
  }

  onProdutoSelecionado(codigoProduto: string): void {
    if (codigoProduto) {
      this.produtoService
        .cosifs(codigoProduto)
        .subscribe((response) => {
          if (response) {
            this.cosifs = response;
            this.desabilitarCamposCosif = false;
          }
        });
    }
  }

  private resetarFormulario(): void {
    this.desabilitarCampos = true;
    this.desabilitarCamposCosif = true;
    this.movimentoManual = {} as MovimentoManualRequest;
  }
}
