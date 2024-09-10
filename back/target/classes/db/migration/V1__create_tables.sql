CREATE  TABLE AGENT (
    id   uuid NOT NULL constraint pk_agent primary key,
    nom varchar(255) NOT NULL,
    prenom  varchar(255) NOT NULL
);

CREATE TABLE AVIS (
    id  uuid NOT NULL constraint pk_avis primary key,
    formation varchar(255) NOT NULL,
    organisme  varchar(255) NOT NULL,
    agent_id   uuid,
    anonyme number NOT NULL,
    date_publication timestamp NOT NULL,
    note_globale integer NOT NULL,
    avis_global varchar(255) NOT NULL,
    constraint check_anonyme CHECK (anonyme between 0 and 1),
    constraint avis_agent foreign key (agent_id) references AGENT (id)
);

CREATE TABLE QUESTION (
    id   uuid NOT NULL constraint pk_question primary key,
    texte varchar(255) NOT NULL
);

CREATE TABLE REPONSE (
    id   uuid NOT NULL constraint pk_reponse primary key,
    avis_id uuid,
    question_id  uuid NOT NULL,
    commentaire varchar(255) NOT NULL,
    note integer not null,
    constraint reponse_question foreign key (question_id) references QUESTION (id),
    constraint fk_reponse_avis foreign key (avis_id) references AVIS(id)
);

