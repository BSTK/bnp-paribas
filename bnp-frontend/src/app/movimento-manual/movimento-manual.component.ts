import {Component, OnInit} from '@angular/core';
import {ProdutoService} from "./produto.service";
import {MovimentoManualService} from "./movimento-manual.service";
import {
  MovimentoManual,
  MovimentoManualRequest,
  Produto,
  ProdutoCosif
} from "./movimento-manual.model";

@Component({
  selector: 'bnp-movimento-manual',
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
    this.carregarProdutos();
    this.carregarMovimentos();
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
      this.carregarMovimentos();
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

  private carregarProdutos(): void {
    this.produtoService
    .produtos()
    .subscribe((produtosResponse) => {
      if (produtosResponse) {
        this.produtos = produtosResponse;
      }
    });
  }

  private carregarMovimentos(): void {
    this.movimentoManualService
    .movimentos()
    .subscribe((movimentosresponse) => {
      if (movimentosresponse) {
        this.movimentos = movimentosresponse;
      }
    });
  }

  private resetarFormulario(): void {
    this.desabilitarCampos = true;
    this.desabilitarCamposCosif = true;
    this.movimentoManual = {} as MovimentoManualRequest;
  }
}
