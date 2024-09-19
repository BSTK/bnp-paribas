import {Component, OnInit} from '@angular/core';
import {ProdutoService} from "./produto.service";
import {MovimentoManualService} from "./movimento-manual.service";
import {MovimentoManual, Produto, ProdutoCosif} from "./movimento-manual.model";
import {forkJoin} from "rxjs";

@Component({
  selector: 'app-movimento-manual',
  templateUrl: './movimento-manual.component.html'
})
export class MovimentoManualComponent implements OnInit {

  desabilitarCampos = true;
  produtos: Produto[] = [];
  cosifs: ProdutoCosif[] = [];
  movimentos: MovimentoManual[] = [];

  constructor(private readonly produtoService: ProdutoService,
              private readonly movimentoManualService: MovimentoManualService) {
  }

  ngOnInit(): void {
    const $produtos = this.produtoService.produtos();
    const $movimentos = this.movimentoManualService.movimentos();
    const $cosifs = this.produtoService.cosifs('P001');

    forkJoin([$cosifs, $produtos, $movimentos])
    .subscribe(([cosifsResponse, produtosResponse, movimentosresponse]) => {
      if (cosifsResponse) { this.cosifs = cosifsResponse; }
      if (produtosResponse) { this.produtos = produtosResponse; }
      if (movimentosresponse) { this.movimentos = movimentosresponse; }
    });
  }

  onNovo(): void {
    this.desabilitarCampos = false;
  }

  onLimpar(): void {
    console.log('Limpar Campos');
  }

  onIncluir(): void {
    console.log('Incluir Movimento');
  }
}
