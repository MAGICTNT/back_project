CREATE TABLE category(
                         id_category SERIAL,
                         title VARCHAR(50)  NOT NULL,
                         description TEXT,
                         PRIMARY KEY(id_category)
);

CREATE TABLE ingredient_category(
                                    id_ingredient_category SERIAL,
                                    title VARCHAR(255)  NOT NULL,
                                    PRIMARY KEY(id_ingredient_category),
                                    UNIQUE(title)
);

CREATE TABLE nutrition(
                          id_nutrition SERIAL,
                          title VARCHAR(150)  NOT NULL,
                          PRIMARY KEY(id_nutrition),
                          UNIQUE(title)
);

CREATE TABLE role(
                     id_role SERIAL,
                     title VARCHAR(50)  NOT NULL,
                     PRIMARY KEY(id_role)
);

CREATE TABLE ingredient(
                           id_ingredient SERIAL,
                           title VARCHAR(255)  NOT NULL,
                           calorie VARCHAR(50)  NOT NULL,
                           id_ingredient_category INTEGER NOT NULL,
                           PRIMARY KEY(id_ingredient),
                           UNIQUE(title),
                           FOREIGN KEY(id_ingredient_category) REFERENCES ingredient_category(id_ingredient_category)
);

CREATE TABLE recipe(
                       id_recipe SERIAL,
                       title VARCHAR(255)  NOT NULL,
                       number_people INTEGER NOT NULL,
                       duration INTEGER NOT NULL,
                       description TEXT,
                       seen INTEGER NOT NULL,
                       picture TEXT,
                       id_nutrition INTEGER NOT NULL,
                       id_category INTEGER NOT NULL,
                       PRIMARY KEY(id_recipe),
                       FOREIGN KEY(id_nutrition) REFERENCES nutrition(id_nutrition),
                       FOREIGN KEY(id_category) REFERENCES category(id_category)
);

CREATE TABLE instruction(
                            id_instruction SERIAL,
                            description TEXT NOT NULL,
                            id_recipe INTEGER NOT NULL,
                            PRIMARY KEY(id_instruction),
                            FOREIGN KEY(id_recipe) REFERENCES recipe(id_recipe)
);

CREATE TABLE consumer(
                         id_consumer SERIAL,
                         pseudo VARCHAR(255)  NOT NULL,
                         mail VARCHAR(255)  NOT NULL,
                         password TEXT NOT NULL,
                         id_role INTEGER NOT NULL,
                         PRIMARY KEY(id_consumer),
                         UNIQUE(pseudo),
                         UNIQUE(mail),
                         FOREIGN KEY(id_role) REFERENCES role(id_role)
);

CREATE TABLE constituted(
                            id_ingredient INTEGER,
                            id_recipe INTEGER,
                            quantity INTEGER NOT NULL,
                            PRIMARY KEY(id_ingredient, id_recipe),
                            FOREIGN KEY(id_ingredient) REFERENCES ingredient(id_ingredient),
                            FOREIGN KEY(id_recipe) REFERENCES recipe(id_recipe)
);

CREATE TABLE allergy(
                        id_ingredient_category INTEGER,
                        id_consumer INTEGER,
                        PRIMARY KEY(id_ingredient_category, id_consumer),
                        FOREIGN KEY(id_ingredient_category) REFERENCES ingredient_category(id_ingredient_category),
                        FOREIGN KEY(id_consumer) REFERENCES consumer(id_consumer)
);

CREATE TABLE liked(
                      id_consumer INTEGER,
                      id_nutrition INTEGER,
                      PRIMARY KEY(id_consumer, id_nutrition),
                      FOREIGN KEY(id_consumer) REFERENCES consumer(id_consumer),
                      FOREIGN KEY(id_nutrition) REFERENCES nutrition(id_nutrition)
);
