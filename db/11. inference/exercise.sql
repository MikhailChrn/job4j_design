CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company (id, name) VALUES (1, 'Alfa');
INSERT INTO company (id, name) VALUES (2, 'Mega');
INSERT INTO company (id, name) VALUES (3, 'Delta');
INSERT INTO company (id, name) VALUES (4, 'Sigma');
INSERT INTO company (id, name) VALUES (5, 'Vector');
INSERT INTO company (id, name) VALUES (6, 'Zero');

INSERT INTO person (id, name, company_id) VALUES (1, 'Ivanov_Ivan', 2);
INSERT INTO person (id, name, company_id) VALUES (2, 'Petrov_Petr', 3);
INSERT INTO person (id, name, company_id) VALUES (3, 'Semenov_Semen', 5);
INSERT INTO person (id, name, company_id) VALUES (4, 'Romanov_Roman', 1);
INSERT INTO person (id, name, company_id) VALUES (5, 'Nikitin_Nikita', 2);
INSERT INTO person (id, name, company_id) VALUES (6, 'Lazarev_Lazar', 3);


SELECT prs.name as "Person name", cmp.name as "Company name"
FROM person prs JOIN company cmp ON prs.id = cmp.id
WHERE cmp.id = 5;


SELECT company.name AS "Company name", COUNT(person.name) "Amount of persons"
FROM company JOIN person ON person.company_id = company.id
GROUP BY company.id
HAVING company.id IN (
    SELECT person.company_id
    FROM person
    GROUP BY person.company_id
    HAVING COUNT(person.id) = (
        SELECT MAX(total)
        FROM (
            SELECT COUNT(company.id) AS total
            FROM company JOIN person ON person.company_id = company.id
            GROUP BY person.company_id
        ) AS result
    )
);
