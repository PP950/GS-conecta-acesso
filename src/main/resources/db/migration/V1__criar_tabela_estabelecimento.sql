CREATE TABLE estabelecimento (
                                 id BIGINT NOT NULL AUTO_INCREMENT,
                                 nome VARCHAR(255),
                                 categoria VARCHAR(255),
                                 endereco VARCHAR(255),
                                 bairro VARCHAR(255),
                                 cidade VARCHAR(255),
                                 contato VARCHAR(255),
                                 PRIMARY KEY (id)
);