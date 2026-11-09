CREATE TABLE relato (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        estabelecimento_id BIGINT NOT NULL,
                        descricao VARCHAR(1000) NOT NULL,
                        status VARCHAR(50) NOT NULL,
                        data_registro DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

                        PRIMARY KEY (id),

                        CONSTRAINT fk_relato_estabelecimento
                            FOREIGN KEY (estabelecimento_id)
                                REFERENCES estabelecimento(id)
);