CREATE TABLE IF NOT EXISTS PRODUTO
(
    COD_PRODUTO CHAR(4) NOT NULL,
    DES_PRODUTO VARCHAR(30),
    STA_STATUS  CHAR(1),
    PRIMARY KEY (COD_PRODUTO)
);

CREATE TABLE IF NOT EXISTS PRODUTO_COSIF
(
    COD_COSIF         CHAR(11) NOT NULL,
    COD_PRODUTO       CHAR(4)  NOT NULL,
    COD_CLASSIFICACAO CHAR(6),
    STA_STATUS        CHAR(1),
    PRIMARY KEY (COD_PRODUTO),
    FOREIGN KEY (COD_PRODUTO) REFERENCES PRODUTO (COD_PRODUTO)
);

CREATE TABLE IF NOT EXISTS MOVIMENTO_MANUAL
(
    DAT_MES        NUMERIC(2)     NOT NULL,
    DAT_ANO        NUMERIC(2)     NOT NULL,
    NUM_LANCAMENTO NUMERIC(18)    NOT NULL,
    COD_PRODUTO    CHAR(4)        NOT NULL,
    COD_COSIF      CHAR(11)       NOT NULL,
    DES_DESCRICAO  VARCHAR(50)    NOT NULL,
    DAT_MOVIMENTO  DATE           NOT NULL,
    COD_USUARIO    VARCHAR(15)    NOT NULL,
    VAL_VALOR      NUMERIC(18, 2) NOT NULL,
    PRIMARY KEY (DAT_MES, DAT_ANO, NUM_LANCAMENTO, COD_PRODUTO, COD_COSIF)
);
