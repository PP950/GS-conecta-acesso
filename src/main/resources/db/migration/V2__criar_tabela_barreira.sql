CREATE TABLE barreira (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          estabelecimento_id BIGINT NOT NULL,
                          tipo VARCHAR(50) NOT NULL,
                          descricao VARCHAR(500),
                          severidade VARCHAR(30),
                          status VARCHAR(30),
                          data_registro DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

                          PRIMARY KEY (id),

                          CONSTRAINT fk_barreira_estabelecimento
                              FOREIGN KEY (estabelecimento_id)
                                  REFERENCES estabelecimento(id)
);