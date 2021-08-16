--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.14
-- Dumped by pg_dump version 10.6 (Ubuntu 10.6-0ubuntu0.18.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: icons; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.icons (
    id integer,
    lat double precision,
    lon double precision,
    icon_type character varying(20),
    status character varying(20),
    evaluation character varying(20),
    image character varying(100),
    object_id integer NOT NULL
);


ALTER TABLE public.icons OWNER TO postgres;

--
-- Data for Name: icons; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.icons (id, lat, lon, icon_type, status, evaluation, image, object_id) FROM stdin;
57	43.7714730000000003	11.2567769999999996	loft	disponibile a breve	3	https://cdn3.iconfinder.com/data/icons/developperss/PNG/Yellow%20Ball.png	100
58	45.4802310000000034	9.21081200000000067	negozio	disponibile a breve	3	https://cdn3.iconfinder.com/data/icons/developperss/PNG/Yellow%20Ball.png	101
59	41.9922559999999976	12.5141200000000001	loft	disponibile a breve	5	https://cdn3.iconfinder.com/data/icons/developperss/PNG/Yellow%20Ball.png	102
60	45.470877999999999	9.20140600000000042	loft	disponibile a breve	4	https://cdn3.iconfinder.com/data/icons/developperss/PNG/Yellow%20Ball.png	103
61	41.9257410000000021	12.5738990000000008	negozio	disponibile a breve	4	https://cdn3.iconfinder.com/data/icons/developperss/PNG/Yellow%20Ball.png	104
62	41.8932610000000025	12.6151509999999991	appartamento	non disponibile	2	https://cdn3.iconfinder.com/data/icons/developperss/PNG/Red%20Ball.png	105
63	45.4697659999999999	9.19247099999999939	loft	non disponibile	4	https://cdn3.iconfinder.com/data/icons/developperss/PNG/Red%20Ball.png	106
64	43.7793109999999999	11.2593130000000006	loft	non disponibile	3	https://cdn3.iconfinder.com/data/icons/developperss/PNG/Red%20Ball.png	107
65	38.1438640000000007	13.3676300000000001	loft	non disponibile	2	https://cdn3.iconfinder.com/data/icons/developperss/PNG/Red%20Ball.png	108
66	41.1377639999999971	16.8424990000000001	negozio	non disponibile	2	https://cdn3.iconfinder.com/data/icons/developperss/PNG/Red%20Ball.png	109
67	43.7702209999999994	11.2544059999999995	negozio	non disponibile	4	https://cdn3.iconfinder.com/data/icons/developperss/PNG/Red%20Ball.png	110
68	45.470362999999999	9.19660900000000048	negozio	non disponibile	5	https://cdn3.iconfinder.com/data/icons/developperss/PNG/Red%20Ball.png	111
69	46.4998709999999988	11.3526710000000008	loft	non disponibile	1	https://cdn3.iconfinder.com/data/icons/developperss/PNG/Red%20Ball.png	112
70	38.0898499999999984	13.3622990000000001	negozio	non disponibile	5	https://cdn3.iconfinder.com/data/icons/developperss/PNG/Red%20Ball.png	113
71	39.2069260000000028	9.12617499999999993	appartamento	non disponibile	4	https://cdn3.iconfinder.com/data/icons/developperss/PNG/Red%20Ball.png	114
72	45.0751840000000001	7.68399400000000021	loft	non disponibile	4	https://cdn3.iconfinder.com/data/icons/developperss/PNG/Red%20Ball.png	115
73	46.5004920000000013	11.3568269999999991	negozio	non disponibile	5	https://cdn3.iconfinder.com/data/icons/developperss/PNG/Red%20Ball.png	116
74	41.120790999999997	16.8816349999999993	appartamento	disponibile	4	https://cdn3.iconfinder.com/data/icons/developperss/PNG/Green%20Ball.png	117
75	45.0637899999999973	7.68714000000000031	appartamento	disponibile	5	https://cdn3.iconfinder.com/data/icons/developperss/PNG/Green%20Ball.png	118
76	43.7804689999999965	11.2694050000000008	appartamento	disponibile	3	https://cdn3.iconfinder.com/data/icons/developperss/PNG/Green%20Ball.png	119
77	41.8860969999999995	12.4011630000000004	negozio	disponibile	4	https://cdn3.iconfinder.com/data/icons/developperss/PNG/Green%20Ball.png	120
78	40.8428210000000007	14.2489039999999996	appartamento	disponibile	1	https://cdn3.iconfinder.com/data/icons/developperss/PNG/Green%20Ball.png	121
79	46.4990409999999983	11.3536409999999997	appartamento	disponibile	4	https://cdn3.iconfinder.com/data/icons/developperss/PNG/Green%20Ball.png	122
80	45.0637899999999973	7.68714000000000031	negozio	disponibile	5	https://cdn3.iconfinder.com/data/icons/developperss/PNG/Green%20Ball.png	123
81	40.8317070000000015	14.2288139999999999	negozio	disponibile	4	https://cdn3.iconfinder.com/data/icons/developperss/PNG/Green%20Ball.png	124
82	41.122444999999999	16.8655059999999999	loft	disponibile	1	https://cdn3.iconfinder.com/data/icons/developperss/PNG/Green%20Ball.png	125
83	45.0707689999999985	7.66605100000000039	loft	disponibile	2	https://cdn3.iconfinder.com/data/icons/developperss/PNG/Green%20Ball.png	126
84	40.8362160000000003	14.2457670000000007	loft	disponibile	3	https://cdn3.iconfinder.com/data/icons/developperss/PNG/Green%20Ball.png	127
\.


--
-- Name: icons icons_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.icons
    ADD CONSTRAINT icons_pkey PRIMARY KEY (object_id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

