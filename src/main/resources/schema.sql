/** SUPPRESION DES TABLES **/

DROP TABLE IF EXISTS public.password_reset_token;
DROP TABLE IF EXISTS public.persistent_logins;
DROP TABLE IF EXISTS public.t_activity;
DROP TABLE IF EXISTS public.t_activityreportmission;
DROP TABLE IF EXISTS public.t_additionalhour;
DROP TABLE IF EXISTS public.t_missionclientcontact;
DROP TABLE IF EXISTS public.t_missionclientcontactdraft;
DROP TABLE IF EXISTS public.t_missionexpenses;
DROP TABLE IF EXISTS public.t_missionexpensesdraft;
DROP TABLE IF EXISTS public.t_news;
DROP TABLE IF EXISTS public.t_activitytype;
DROP TABLE IF EXISTS public.t_clientcontact;
DROP TABLE IF EXISTS public.t_expenses;
DROP TABLE IF EXISTS public.t_expensestype;
DROP TABLE IF EXISTS public.t_mission;
DROP TABLE IF EXISTS public.t_missiondraft;
DROP TABLE IF EXISTS public.t_observation;
DROP TABLE IF EXISTS public.t_activityreport;
DROP TABLE IF EXISTS public.t_client;
DROP TABLE IF EXISTS public.t_company;
DROP TABLE IF EXISTS public.t_user;
DROP TABLE IF EXISTS public.t_authority;


/** CREATION DES TABLES **/

CREATE TABLE public.password_reset_token (
    id SERIAL NOT NULL,
    token varchar(255) NOT NULL,
    user_id integer NOT NULL,
    expiry_date timestamp with time zone NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.persistent_logins (
    username varchar(64) NOT NULL,
    series varchar(64) NOT NULL,
    token varchar(64) NOT NULL,
    last_used timestamp NOT NULL,
    PRIMARY KEY (series)
);


CREATE TABLE public.t_activity (
    id SERIAL NOT NULL,
    day integer NOT NULL,
    value double precision NOT NULL,
    mission_id integer,
    activityreport_id integer NOT NULL,
    activitytype_id integer NOT NULL
);

CREATE TABLE public.t_activityreport (
    id SERIAL NOT NULL,
    month integer NOT NULL,
    year integer NOT NULL,
    state character varying(255) NOT NULL,
    user_id integer NOT NULL,
    creation_date timestamp with time zone NOT NULL,
    submission_date timestamp with time zone,
    validation_date timestamp with time zone
);

CREATE TABLE public.t_activityreportmission (
    mission_id integer NOT NULL,
    activityreport_id integer NOT NULL
);

CREATE TABLE public.t_activitytype (
    id SERIAL NOT NULL,
    name character varying(255) NOT NULL,
    activated boolean NOT NULL
);

CREATE TABLE public.t_additionalhour (
    id SERIAL NOT NULL,
    event_date date NOT NULL,
    hours double precision NOT NULL,
    comment character varying(255),
    state character varying(50) NOT NULL,
    user_id integer NOT NULL,
    mission_id integer NOT NULL
);

CREATE TABLE public.t_authority (
    name character varying(50) NOT NULL
);

CREATE TABLE public.t_client (
    id SERIAL NOT NULL,
    corporate_name character(255) NOT NULL,
    address1 text NOT NULL,
    address2 text,
    address3 text,
    postal_code character varying(15) NOT NULL,
    city character varying(255) NOT NULL,
    phone_number character varying(50) NOT NULL,
    activated boolean NOT NULL
);

CREATE TABLE public.t_clientcontact (
    id SERIAL NOT NULL,
    civility character varying(50) NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    "position" character varying(255),
    phone_number character varying(50) NOT NULL,
    mobile_number character varying(50),
    email character varying(255) NOT NULL,
    department character varying(255),
    client_id integer NOT NULL
);

CREATE TABLE public.t_company (
    id SERIAL NOT NULL,
    corporate_name character varying(255) NOT NULL,
    full_corporate_name character varying(255) NOT NULL,
    address1 text NOT NULL,
    address2 text,
    address3 text,
    postal_code character varying(15) NOT NULL,
    city character varying(255) NOT NULL,
    phone_number character varying(50) NOT NULL,
    email character varying(255) NOT NULL,
    logo character varying(255) NOT NULL,
    legal_notice1 text,
    legal_notice2 text
);

CREATE TABLE public.t_expenses (
    id SERIAL NOT NULL,
    description character varying(255) NOT NULL,
    activated boolean NOT NULL,
    expensestype_id integer NOT NULL
);

CREATE TABLE public.t_expensestype (
    id SERIAL NOT NULL,
    name character varying(255) NOT NULL,
    activated boolean NOT NULL
);

CREATE TABLE public.t_mission (
    id SERIAL NOT NULL,
    reference character varying(255) NOT NULL,
    planned_start_date date NOT NULL,
    planned_end_date date NOT NULL,
    renewable boolean,
    weekly_working_time character varying(255) NOT NULL,
    functional_area text NOT NULL,
    technical_area text NOT NULL,
    task text NOT NULL,
    state character varying(50) NOT NULL,
    creation_date date NOT NULL,
    user_id integer NOT NULL,
    client_id integer NOT NULL,
    manager_id integer NOT NULL,
    company_id integer NOT NULL,
	signatory_id integer NOT NULL
);

CREATE TABLE public.t_missiondraft (
    id SERIAL NOT NULL,
    reference character varying(255) NOT NULL,
    planned_start_date date,
    planned_end_date date,
    renewable boolean,
    weekly_working_time character varying(255),
    functional_area text,
    technical_area text,
    task text,
    state character varying(50),
    creation_date date,
    user_id integer,
    client_id integer,
    manager_id integer,
    company_id integer,
	signatory_id integer
);

CREATE TABLE public.t_missionclientcontact (
    mission_id integer NOT NULL,
    clientcontact_id integer NOT NULL
);

CREATE TABLE public.t_missionclientcontactdraft (
    missiondraft_id integer NOT NULL,
    clientcontact_id integer NOT NULL
);

CREATE TABLE public.t_missionexpenses (
    mission_id integer NOT NULL,
    expenses_id integer NOT NULL
);

CREATE TABLE public.t_missionexpensesdraft (
    missiondraft_id integer NOT NULL,
    expenses_id integer NOT NULL
);

CREATE TABLE public.t_news (
    id SERIAL NOT NULL,
    title character varying(255) NOT NULL,
    content text NOT NULL,
    resource character varying(255),
    creation_date timestamp with time zone NOT NULL,
    activated boolean NOT NULL
);

CREATE TABLE public.t_observation (
    id SERIAL NOT NULL,
    activityreport_id integer,
    user_id integer,
    comment text,
    publication_date timestamp with time zone
);

CREATE TABLE public.t_user (
    id SERIAL NOT NULL,
    login character varying(50) NOT NULL,
    password character varying(100) NOT NULL,
    civility character varying(50) NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    address1 text NOT NULL,
    address2 text,
    address3 text,
    postal_code character varying(15) NOT NULL,
    city character varying(255) NOT NULL,
    mobile_number character varying(50),
    position text,
    activated boolean NOT NULL,
    creation_date timestamp with time zone NOT NULL,
    authority_name character varying(50) NOT NULL
);

/** CREATION DES CLES PRIMAIRES **/

ALTER TABLE public.t_activityreportmission
    ADD CONSTRAINT t_activityreportmission_pkey PRIMARY KEY (mission_id, activityreport_id);

ALTER TABLE public.t_missionclientcontact
    ADD CONSTRAINT t_missionclientcontact_pkey PRIMARY KEY (mission_id, clientcontact_id);

ALTER TABLE public.t_missionclientcontactdraft
    ADD CONSTRAINT t_missionclientcontactdraft_pkey PRIMARY KEY (missiondraft_id, clientcontact_id);

ALTER TABLE public.t_missionexpenses
    ADD CONSTRAINT t_missionexpenses_pkey PRIMARY KEY (expenses_id, mission_id);

ALTER TABLE public.t_missionexpensesdraft
    ADD CONSTRAINT t_missionexpensesdraft_pkey PRIMARY KEY (expenses_id, missiondraft_id);


/** CREATION DES CONTRAINTES UNIQUES **/

ALTER TABLE public.t_user
    ADD CONSTRAINT "t_user_login_uKey" UNIQUE (login);


/** CREATION DES CLES ETRANGERES **/

ALTER TABLE ONLY public.password_reset_token
    ADD CONSTRAINT "password_reset_token_user_id_fKey" FOREIGN KEY (user_id) REFERENCES public.t_user(id) ON DELETE RESTRICT;

ALTER TABLE public.t_activity
    ADD CONSTRAINT "t_activity_activityreport_id_fKey" FOREIGN KEY (activityreport_id) REFERENCES public.t_activityreport(id) ON DELETE RESTRICT;

ALTER TABLE public.t_activity
    ADD CONSTRAINT "t_activity_activitytype_id_fKey" FOREIGN KEY (activitytype_id) REFERENCES public.t_activitytype(id) ON DELETE RESTRICT;

ALTER TABLE public.t_activity
    ADD CONSTRAINT "t_activity_mission_id_fKey" FOREIGN KEY (mission_id) REFERENCES public.t_mission(id) ON DELETE RESTRICT;

ALTER TABLE public.t_activityreport
    ADD CONSTRAINT "t_activityreport_user_id_fKey" FOREIGN KEY (user_id) REFERENCES public.t_user(id) ON DELETE RESTRICT;

ALTER TABLE public.t_activityreportmission
    ADD CONSTRAINT "t_activityreportmission_activityreport_id_fKey" FOREIGN KEY (activityreport_id) REFERENCES public.t_activityreport(id) ON DELETE RESTRICT;

ALTER TABLE public.t_activityreportmission
    ADD CONSTRAINT "t_activityreportmission_mission_id_fKey" FOREIGN KEY (mission_id) REFERENCES public.t_mission(id) ON DELETE RESTRICT;

ALTER TABLE public.t_additionalhour
    ADD CONSTRAINT "t_additionalhour_mission_id_fKey" FOREIGN KEY (mission_id) REFERENCES public.t_mission(id) ON DELETE RESTRICT;

ALTER TABLE public.t_additionalhour
    ADD CONSTRAINT "t_additionalhour_user_id_fKey" FOREIGN KEY (user_id) REFERENCES public.t_user(id) ON DELETE RESTRICT;

ALTER TABLE public.t_clientcontact
    ADD CONSTRAINT "t_clientcontact_client_id_fKey" FOREIGN KEY (client_id) REFERENCES public.t_client(id) ON DELETE RESTRICT;

ALTER TABLE public.t_expenses
    ADD CONSTRAINT "t_expenses_expensestype_id_fKey" FOREIGN KEY (expensestype_id) REFERENCES public.t_expensestype(id) ON DELETE RESTRICT;

ALTER TABLE public.t_mission
    ADD CONSTRAINT "t_mission_client_id_fKey" FOREIGN KEY (client_id) REFERENCES public.t_client(id) ON DELETE RESTRICT;

ALTER TABLE public.t_mission
    ADD CONSTRAINT "t_mission_company_id_fKey" FOREIGN KEY (company_id) REFERENCES public.t_company(id) ON DELETE RESTRICT;

ALTER TABLE public.t_mission
    ADD CONSTRAINT "t_mission_manager_id_fKey" FOREIGN KEY (manager_id) REFERENCES public.t_user(id) ON DELETE RESTRICT;

ALTER TABLE public.t_mission
    ADD CONSTRAINT "t_mission_user_id_fKey" FOREIGN KEY (user_id) REFERENCES public.t_user(id) ON DELETE RESTRICT;

ALTER TABLE public.t_mission
    ADD CONSTRAINT "t_mission_signatory_id_fKey" FOREIGN KEY (user_id) REFERENCES public.t_user(id) ON DELETE RESTRICT;

ALTER TABLE public.t_missiondraft
    ADD CONSTRAINT "t_missiondraft_client_id_fKey" FOREIGN KEY (client_id) REFERENCES public.t_client(id) ON DELETE RESTRICT;

ALTER TABLE public.t_missiondraft
    ADD CONSTRAINT "t_missiondraft_company_id_fKey" FOREIGN KEY (company_id) REFERENCES public.t_company(id) ON DELETE RESTRICT;

ALTER TABLE public.t_missiondraft
    ADD CONSTRAINT "t_missiondraft_manager_id_fKey" FOREIGN KEY (manager_id) REFERENCES public.t_user(id) ON DELETE RESTRICT;

ALTER TABLE public.t_missiondraft
    ADD CONSTRAINT "t_missiondraft_user_id_fKey" FOREIGN KEY (user_id) REFERENCES public.t_user(id) ON DELETE RESTRICT;

ALTER TABLE public.t_missiondraft
    ADD CONSTRAINT "t_missiondraft_signatory_id_fKey" FOREIGN KEY (user_id) REFERENCES public.t_user(id) ON DELETE RESTRICT;

ALTER TABLE public.t_missionclientcontact
    ADD CONSTRAINT "t_missionclientcontact_clientcontact_id_fKey" FOREIGN KEY (clientcontact_id) REFERENCES public.t_clientcontact(id) ON DELETE RESTRICT;

ALTER TABLE public.t_missionclientcontact
    ADD CONSTRAINT "t_missionclientcontact_mission_id_fKey" FOREIGN KEY (mission_id) REFERENCES public.t_mission(id) ON DELETE RESTRICT;

ALTER TABLE public.t_missionclientcontactdraft
    ADD CONSTRAINT "t_missionclientcontactdraft_clientcontact_id_fKey" FOREIGN KEY (clientcontact_id) REFERENCES public.t_clientcontact(id) ON DELETE RESTRICT;

ALTER TABLE public.t_missionclientcontactdraft
    ADD CONSTRAINT "t_missionclientcontactdraft_mission_id_fKey" FOREIGN KEY (missiondraft_id) REFERENCES public.t_missiondraft(id) ON DELETE RESTRICT;

ALTER TABLE public.t_missionexpenses
    ADD CONSTRAINT "t_missionexpenses_clientcontact_id_fKey" FOREIGN KEY (expenses_id) REFERENCES public.t_expenses(id) ON DELETE RESTRICT;

ALTER TABLE public.t_missionexpenses
    ADD CONSTRAINT "t_missionexpenses_mission_id_fKey" FOREIGN KEY (mission_id) REFERENCES public.t_mission(id) ON DELETE RESTRICT;

ALTER TABLE public.t_missionexpensesdraft
    ADD CONSTRAINT "t_missionexpensesdraft_clientcontact_id_fKey" FOREIGN KEY (expenses_id) REFERENCES public.t_expenses(id) ON DELETE RESTRICT;

ALTER TABLE public.t_missionexpensesdraft
    ADD CONSTRAINT "t_missionexpensesdraft_mission_id_fKey" FOREIGN KEY (missiondraft_id) REFERENCES public.t_missiondraft(id) ON DELETE RESTRICT;

ALTER TABLE public.t_observation
    ADD CONSTRAINT "t_observation_activityreport_id_fKey" FOREIGN KEY (activityreport_id) REFERENCES public.t_activityreport(id) ON DELETE RESTRICT;

ALTER TABLE public.t_observation
    ADD CONSTRAINT "t_observation_user_id_fKey" FOREIGN KEY (user_id) REFERENCES public.t_user(id) ON DELETE RESTRICT;

ALTER TABLE public.t_user
    ADD CONSTRAINT "t_user_authority_name_fKey" FOREIGN KEY (authority_name) REFERENCES public.t_authority(name) ON DELETE RESTRICT;
