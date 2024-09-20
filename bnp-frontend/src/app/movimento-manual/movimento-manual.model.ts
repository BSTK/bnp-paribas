export interface ProdutoCosif {
  codigoCosif: string;
  codigoClassificacao: string;
}

export interface Produto {
  codigo: string;
  descricao: string;
}

export interface MovimentoManualRequest {
  mes: string;
  ano: string;
  valor: number;
  descricao: string;
  codigoProduto: string;
  codigoCosif: string;
}

export interface MovimentoManual {
  mes: string;
  ano: string;
  valor: number;
  descricao: string;
  codigoProduto: string;
  descricaoProduto: string;
  numeroLancamento: string;
}

