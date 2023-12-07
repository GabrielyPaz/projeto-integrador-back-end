
--liquibase formatted sql

--changeset 20231207-1 author:gabrielysouza129@gmail.com label:insert-data
INSERT INTO `funcao`(id, nome) VALUES (UUID_TO_BIN('b4c12a51-d65d-492b-abee-283172ed75cf'),'ADM');

INSERT INTO `usuario`(id, id_funcao, email, nome, sobrenome, senha) VALUES (UUID_TO_BIN('e3620689-ba65-4fee-8c9a-f66d33b936f9'), UUID_TO_BIN('b4c12a51-d65d-492b-abee-283172ed75cf'),'gabriely@email.com', 'Gabriely', 'Paz','$2a$10$ZiOC8VWL/v7Xc2vnG.nFguCybEpbPBP/EGkI6pLWlBKFgWGXCeThG');
INSERT INTO `usuario`(id, id_funcao, email, nome, sobrenome, senha) VALUES (UUID_TO_BIN('72b4df91-cab5-4b51-8b5a-1eba1b83feab'), UUID_TO_BIN('b4c12a51-d65d-492b-abee-283172ed75cf'),'gabrielypaz@email.com', 'Gabriely', 'Paz','$2a$10$ZiOC8VWL/v7Xc2vnG.nFguCybEpbPBP/EGkI6pLWlBKFgWGXCeThG');

INSERT INTO `cidades`(id, estado, nome) VALUES (UUID_TO_BIN('743ca0af-9e53-472f-b7ff-27f3e286e670'),'SP','Sao paulo');
INSERT INTO `cidades`(id, estado, nome) VALUES (UUID_TO_BIN('4c2c6615-f95d-4da0-9813-f8d14b15fdd8'),'PE','Recife');

INSERT INTO `caracteristicas`(id, icone_url, nome) VALUES (UUID_TO_BIN('974089b5-41cb-4337-9d86-2bd46ccd79eb'),'AUTOMATICO','câmbio');
INSERT INTO `caracteristicas`(id, icone_url, nome) VALUES (UUID_TO_BIN('a63a35fd-0d33-4608-929a-050bf3a5f73a'),'MANUAL','câmbio');
INSERT INTO `caracteristicas`(id, icone_url, nome) VALUES (UUID_TO_BIN('be9b716e-c4ad-4939-96c1-50d858e9f3e3'),'DIRECAO_HIDRAULICA','direção');
INSERT INTO `caracteristicas`(id, icone_url, nome) VALUES (UUID_TO_BIN('57fcf8f3-1432-4978-8430-5a5f9bcb6f7b'),'AR_CONDICIONADO','ar condicionado');

INSERT INTO `categorias`(id, nome, qualificacao, descricao, url_imagem) VALUES (UUID_TO_BIN('3de65668-83a0-4722-817b-0ed123483ac3'),'SUV','cinco estrelas','Uma experiência única de direção', 'https://cdn.motor1.com/images/mgl/7ZvQ3V/s3/011.jpg');
INSERT INTO `categorias`(id, nome, qualificacao, descricao, url_imagem) VALUES (UUID_TO_BIN('cb4c7601-466b-4a92-9b7d-5e3b064fe031'),'Confort','quatro estrelas','Uma experiência única de viagem','https://autoagora.com.br/wp-content/uploads/2022/12/1-avaliacao-vw-virtus-highline-comfortline-2022-autoagora-com-br.jpg.jpg');
INSERT INTO `categorias`(id, nome, qualificacao, descricao, url_imagem) VALUES (UUID_TO_BIN('f7cde4df-fc56-47f1-ad96-af3731b05a02'),'HATCH','cinco estrelas','confortaveis da categoria hatch', 'https://www.automaistv.com.br/wp-content/uploads/2021/11/Honda-City-2022-2_edited-990x594.jpg');


