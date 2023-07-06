-- Inserção na tabela category
INSERT INTO category (description) VALUES ('Categoria 1');
INSERT INTO category (description) VALUES ('Categoria 2');
INSERT INTO category (description) VALUES ('Categoria 3');
INSERT INTO category (description) VALUES ('Categoria 4');
-- Adicione mais inserções conforme necessário para outras categorias

-- Inserção na tabela supplier
INSERT INTO supplier (name) VALUES ('Fornecedor 1');
INSERT INTO supplier (name) VALUES ('Fornecedor 2');
-- Adicione mais inserções conforme necessário para outros fornecedores

-- Inserção na tabela product
INSERT INTO product (name, fk_category, fk_supplier, quantity_available) VALUES ('Produto 1', 1, 1,10);
INSERT INTO product (name, fk_category, fk_supplier, quantity_available) VALUES ('Produto 2', 2, 2,5);
INSERT INTO product (name, fk_category, fk_supplier, quantity_available) VALUES ('Produto 2', 3, 2,3);
INSERT INTO product (name, fk_category, fk_supplier, quantity_available) VALUES ('Produto 2', 4, 1,7);
-- Adicione mais inserções conforme necessário para outros produtos
