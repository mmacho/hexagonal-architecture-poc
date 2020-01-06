DROP TABLE IF EXISTS example_entities;

CREATE TABLE example_entities (
	id		INTEGER PRIMARY KEY AUTO_INCREMENT,
	name	VARCHAR(30) DEFAULT '' NOT NULL
);

INSERT INTO example_entities (id, name) VALUES (1, 'entity_1');
INSERT INTO example_entities (id, name) VALUES (2, 'entity_2');
INSERT INTO example_entities (id, name) VALUES (3, 'entity_3');
INSERT INTO example_entities (id, name) VALUES (4, 'entity_4');

