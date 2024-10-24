-- Insertion des rôles
INSERT INTO role (title) VALUES ('admin'), ('user');

-- Insertion des catégories de recettes
INSERT INTO category (title, description) VALUES
                                              ('Dessert', 'Recettes sucrées pour les amateurs de sucreries'),
                                              ('Plat principal', 'Recettes complètes pour le déjeuner ou le dîner');

-- Insertion des catégories d'ingrédients
INSERT INTO ingredient_category (title) VALUES
                                            ('Légumes'),
                                            ('Fruits'),
                                            ('Viandes'),
                                            ('Produits laitiers');

-- Insertion des nutriments
INSERT INTO nutrition (title) VALUES
                                  ('Protéines'),
                                  ('Glucides'),
                                  ('Lipides'),
                                  ('Vitamines');

-- Insertion des ingrédients
INSERT INTO ingredient (title, calorie, id_ingredient_category) VALUES
                                                                    ('Tomate', '18 kcal', 1),
                                                                    ('Pomme', '52 kcal', 2),
                                                                    ('Poulet', '165 kcal', 3),
                                                                    ('Lait', '42 kcal', 4);

-- Insertion des recettes
INSERT INTO recipe (title, number_people, duration, description, seen, picture, id_nutrition, id_category) VALUES
                                                                                                               ('Salade de tomates', 4, 15, 'Salade fraîche avec des tomates et de la vinaigrette.', 100, 'salade.jpg', 1, 2),
                                                                                                               ('Tarte aux pommes', 6, 60, 'Tarte croustillante aux pommes caramélisées.', 150, 'tarte_pommes.jpg', 2, 1);

-- Insertion des instructions
INSERT INTO instruction (description, id_recipe) VALUES
                                                     ('Couper les tomates en dés.', 1),
                                                     ('Mélanger avec de l''huile d''olive et du vinaigre.', 1),
                                                     ('Éplucher et couper les pommes.', 2),
                                                     ('Préparer une pâte brisée et disposer les pommes.', 2);

-- Insertion des consommateurs
INSERT INTO consumer (pseudo, mail, password, id_role) VALUES
                                                           ('alice', 'alice@example.com', 'password123', 2),
                                                           ('bob', 'bob@example.com', 'password456', 2),
                                                           ('admin', 'admin@example.com', 'adminpass', 1);

-- Insertion des ingrédients dans les recettes (constitués)
INSERT INTO constituted (id_ingredient, id_recipe, quantity) VALUES
                                                                 (1, 1, 3),  -- 3 tomates pour la recette de salade de tomates
                                                                 (2, 2, 5);  -- 5 pommes pour la tarte aux pommes

-- Insertion des allergies
INSERT INTO allergy (id_ingredient_category, id_consumer) VALUES
                                                              (3, 1),  -- Alice est allergique aux viandes
                                                              (4, 2);  -- Bob est allergique aux produits laitiers

-- Insertion des préférences nutritionnelles (likes)
INSERT INTO liked (id_consumer, id_nutrition) VALUES
                                                  (1, 1),  -- Alice aime les protéines
                                                  (2, 2);  -- Bob aime les glucides