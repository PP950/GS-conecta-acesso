CREATE TABLE parceiro (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          nome VARCHAR(150) NOT NULL,
                          especialidade VARCHAR(50) NOT NULL,
                          descricao VARCHAR(500),
                          telefone VARCHAR(20),
                          email VARCHAR(150),
                          bairro VARCHAR(100),
                          cidade VARCHAR(100),

                          PRIMARY KEY (id)
);