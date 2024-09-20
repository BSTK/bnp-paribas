export const baseUrl: string = 'http://localhost:8080';

export const environment = {
  production: true,
  produtos: `${baseUrl}/v1/produtos`,
  produtosCosif: `${baseUrl}/v1/produtos/cosif`,
  movimentos: `${baseUrl}/v1/movimento-manual`
};
