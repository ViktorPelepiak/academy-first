--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.12
-- Dumped by pg_dump version 9.6.12

-- Started on 2019-11-19 17:46:15

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
-- TOC entry 1 (class 3079 OID 12387)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2192 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 186 (class 1259 OID 76536)
-- Name: auditories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.auditories (
    auditory_id integer NOT NULL,
    building_number integer NOT NULL,
    floor integer NOT NULL,
    auditory_number character varying(10) NOT NULL
);


ALTER TABLE public.auditories OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 76534)
-- Name: auditories_auditory_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.auditories_auditory_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.auditories_auditory_id_seq OWNER TO postgres;

--
-- TOC entry 2193 (class 0 OID 0)
-- Dependencies: 185
-- Name: auditories_auditory_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.auditories_auditory_id_seq OWNED BY public.auditories.auditory_id;


--
-- TOC entry 193 (class 1259 OID 76565)
-- Name: groups; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.groups (
    group_id integer NOT NULL,
    faculty character varying(15) NOT NULL,
    specialisation character varying(20) NOT NULL,
    group_number character varying(10) NOT NULL,
    course integer NOT NULL
);


ALTER TABLE public.groups OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 76563)
-- Name: groups_group_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.groups_group_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.groups_group_id_seq OWNER TO postgres;

--
-- TOC entry 2194 (class 0 OID 0)
-- Dependencies: 192
-- Name: groups_group_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.groups_group_id_seq OWNED BY public.groups.group_id;


--
-- TOC entry 195 (class 1259 OID 76573)
-- Name: lessons; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.lessons (
    lesson_id bigint NOT NULL,
    lesson_time_id integer NOT NULL,
    group_id integer NOT NULL,
    lesson_type integer NOT NULL,
    teacher_id integer NOT NULL,
    auditory_id integer NOT NULL,
    week_parity_id integer NOT NULL,
    day_of_week_id integer NOT NULL,
    subject_id integer NOT NULL
);


ALTER TABLE public.lessons OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 76571)
-- Name: lessons_lesson_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.lessons_lesson_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lessons_lesson_id_seq OWNER TO postgres;

--
-- TOC entry 2195 (class 0 OID 0)
-- Dependencies: 194
-- Name: lessons_lesson_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.lessons_lesson_id_seq OWNED BY public.lessons.lesson_id;


--
-- TOC entry 189 (class 1259 OID 76550)
-- Name: lessons_time; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.lessons_time (
    lesson_number integer NOT NULL,
    begin_time time without time zone NOT NULL,
    end_time time without time zone NOT NULL
);


ALTER TABLE public.lessons_time OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 76544)
-- Name: subjects; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.subjects (
    subject_id integer NOT NULL,
    name character varying(30) NOT NULL
);


ALTER TABLE public.subjects OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 76542)
-- Name: subjects_subject_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.subjects_subject_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.subjects_subject_id_seq OWNER TO postgres;

--
-- TOC entry 2196 (class 0 OID 0)
-- Dependencies: 187
-- Name: subjects_subject_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.subjects_subject_id_seq OWNED BY public.subjects.subject_id;


--
-- TOC entry 191 (class 1259 OID 76557)
-- Name: teachers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.teachers (
    teacher_id integer NOT NULL,
    first_name character varying(15) NOT NULL,
    last_name character varying(15) NOT NULL,
    father_name character varying(15) NOT NULL,
    date_of_birth date NOT NULL,
    info character varying(200)
);


ALTER TABLE public.teachers OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 76555)
-- Name: teachers_teacher_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.teachers_teacher_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.teachers_teacher_id_seq OWNER TO postgres;

--
-- TOC entry 2197 (class 0 OID 0)
-- Dependencies: 190
-- Name: teachers_teacher_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.teachers_teacher_id_seq OWNED BY public.teachers.teacher_id;


--
-- TOC entry 2029 (class 2604 OID 76539)
-- Name: auditories auditory_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.auditories ALTER COLUMN auditory_id SET DEFAULT nextval('public.auditories_auditory_id_seq'::regclass);


--
-- TOC entry 2032 (class 2604 OID 76568)
-- Name: groups group_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups ALTER COLUMN group_id SET DEFAULT nextval('public.groups_group_id_seq'::regclass);


--
-- TOC entry 2033 (class 2604 OID 76576)
-- Name: lessons lesson_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lessons ALTER COLUMN lesson_id SET DEFAULT nextval('public.lessons_lesson_id_seq'::regclass);


--
-- TOC entry 2030 (class 2604 OID 76547)
-- Name: subjects subject_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subjects ALTER COLUMN subject_id SET DEFAULT nextval('public.subjects_subject_id_seq'::regclass);


--
-- TOC entry 2031 (class 2604 OID 76560)
-- Name: teachers teacher_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.teachers ALTER COLUMN teacher_id SET DEFAULT nextval('public.teachers_teacher_id_seq'::regclass);


--
-- TOC entry 2175 (class 0 OID 76536)
-- Dependencies: 186
-- Data for Name: auditories; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.auditories (auditory_id, building_number, floor, auditory_number) VALUES (1, 1, 1, '0');
INSERT INTO public.auditories (auditory_id, building_number, floor, auditory_number) VALUES (2, 1, 1, '1');
INSERT INTO public.auditories (auditory_id, building_number, floor, auditory_number) VALUES (3, 1, 1, '2');
INSERT INTO public.auditories (auditory_id, building_number, floor, auditory_number) VALUES (4, 1, 1, '3');
INSERT INTO public.auditories (auditory_id, building_number, floor, auditory_number) VALUES (5, 1, 1, '4');
INSERT INTO public.auditories (auditory_id, building_number, floor, auditory_number) VALUES (6, 1, 2, '5');
INSERT INTO public.auditories (auditory_id, building_number, floor, auditory_number) VALUES (7, 1, 2, '6');
INSERT INTO public.auditories (auditory_id, building_number, floor, auditory_number) VALUES (8, 1, 2, '7');
INSERT INTO public.auditories (auditory_id, building_number, floor, auditory_number) VALUES (10, 1, 2, '9');
INSERT INTO public.auditories (auditory_id, building_number, floor, auditory_number) VALUES (11, 1, 2, '21');
INSERT INTO public.auditories (auditory_id, building_number, floor, auditory_number) VALUES (9, 1, 2, '8a');


--
-- TOC entry 2198 (class 0 OID 0)
-- Dependencies: 185
-- Name: auditories_auditory_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.auditories_auditory_id_seq', 13, true);


--
-- TOC entry 2182 (class 0 OID 76565)
-- Dependencies: 193
-- Data for Name: groups; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.groups (group_id, faculty, specialisation, group_number, course) VALUES (3, 'FMI', 'Spec_2', '402', 4);
INSERT INTO public.groups (group_id, faculty, specialisation, group_number, course) VALUES (4, 'FMI', 'Spec_3', '403', 4);
INSERT INTO public.groups (group_id, faculty, specialisation, group_number, course) VALUES (1, 'FMI', 'MatMode', '400', 4);
INSERT INTO public.groups (group_id, faculty, specialisation, group_number, course) VALUES (9, 'FMI', 'SystemAnalitic', '407', 4);
INSERT INTO public.groups (group_id, faculty, specialisation, group_number, course) VALUES (6, 'FMI', 'AMIT', '402', 4);
INSERT INTO public.groups (group_id, faculty, specialisation, group_number, course) VALUES (12, 'FMI', 'AMIT(short form)', '422', 4);


--
-- TOC entry 2199 (class 0 OID 0)
-- Dependencies: 192
-- Name: groups_group_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.groups_group_id_seq', 13, true);


--
-- TOC entry 2184 (class 0 OID 76573)
-- Dependencies: 195
-- Data for Name: lessons; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.lessons (lesson_id, lesson_time_id, group_id, lesson_type, teacher_id, auditory_id, week_parity_id, day_of_week_id, subject_id) VALUES (5, 3, 1, 0, 1, 1, 0, 1, 5);
INSERT INTO public.lessons (lesson_id, lesson_time_id, group_id, lesson_type, teacher_id, auditory_id, week_parity_id, day_of_week_id, subject_id) VALUES (3, 2, 3, 0, 3, 3, 1, 1, 3);
INSERT INTO public.lessons (lesson_id, lesson_time_id, group_id, lesson_type, teacher_id, auditory_id, week_parity_id, day_of_week_id, subject_id) VALUES (4, 4, 1, 0, 1, 2, 0, 1, 4);
INSERT INTO public.lessons (lesson_id, lesson_time_id, group_id, lesson_type, teacher_id, auditory_id, week_parity_id, day_of_week_id, subject_id) VALUES (1, 1, 1, 0, 1, 4, 1, 1, 1);
INSERT INTO public.lessons (lesson_id, lesson_time_id, group_id, lesson_type, teacher_id, auditory_id, week_parity_id, day_of_week_id, subject_id) VALUES (2, 2, 1, 0, 1, 5, 2, 1, 2);
INSERT INTO public.lessons (lesson_id, lesson_time_id, group_id, lesson_type, teacher_id, auditory_id, week_parity_id, day_of_week_id, subject_id) VALUES (6, 4, 6, 1, 2, 9, 1, 4, 5);
INSERT INTO public.lessons (lesson_id, lesson_time_id, group_id, lesson_type, teacher_id, auditory_id, week_parity_id, day_of_week_id, subject_id) VALUES (7, 5, 6, 0, 2, 1, 2, 4, 1);
INSERT INTO public.lessons (lesson_id, lesson_time_id, group_id, lesson_type, teacher_id, auditory_id, week_parity_id, day_of_week_id, subject_id) VALUES (9, 3, 6, 1, 6, 11, 0, 1, 6);
INSERT INTO public.lessons (lesson_id, lesson_time_id, group_id, lesson_type, teacher_id, auditory_id, week_parity_id, day_of_week_id, subject_id) VALUES (10, 3, 6, 1, 6, 11, 0, 2, 6);


--
-- TOC entry 2200 (class 0 OID 0)
-- Dependencies: 194
-- Name: lessons_lesson_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.lessons_lesson_id_seq', 10, true);


--
-- TOC entry 2178 (class 0 OID 76550)
-- Dependencies: 189
-- Data for Name: lessons_time; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.lessons_time (lesson_number, begin_time, end_time) VALUES (1, '08:20:00', '09:40:00');
INSERT INTO public.lessons_time (lesson_number, begin_time, end_time) VALUES (2, '09:50:00', '11:10:00');
INSERT INTO public.lessons_time (lesson_number, begin_time, end_time) VALUES (3, '11:30:00', '12:50:00');
INSERT INTO public.lessons_time (lesson_number, begin_time, end_time) VALUES (4, '13:00:00', '14:20:00');
INSERT INTO public.lessons_time (lesson_number, begin_time, end_time) VALUES (5, '14:30:00', '15:50:00');


--
-- TOC entry 2177 (class 0 OID 76544)
-- Dependencies: 188
-- Data for Name: subjects; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.subjects (subject_id, name) VALUES (2, 'Sub_1');
INSERT INTO public.subjects (subject_id, name) VALUES (3, 'Sub_2');
INSERT INTO public.subjects (subject_id, name) VALUES (4, 'Sub_3');
INSERT INTO public.subjects (subject_id, name) VALUES (5, 'Sub_4');
INSERT INTO public.subjects (subject_id, name) VALUES (1, 'subject');
INSERT INTO public.subjects (subject_id, name) VALUES (6, 'Comp.Graphic');
INSERT INTO public.subjects (subject_id, name) VALUES (7, 'Java Fundamentals');


--
-- TOC entry 2201 (class 0 OID 0)
-- Dependencies: 187
-- Name: subjects_subject_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.subjects_subject_id_seq', 8, true);


--
-- TOC entry 2180 (class 0 OID 76557)
-- Dependencies: 191
-- Data for Name: teachers; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.teachers (teacher_id, first_name, last_name, father_name, date_of_birth, info) VALUES (1, 'FN_0', 'LN_0', 'FthN_0', '1980-01-01', 'some teacher');
INSERT INTO public.teachers (teacher_id, first_name, last_name, father_name, date_of_birth, info) VALUES (2, 'FN_1', 'LN_1', 'FthN_1', '1980-02-01', 'some teacher');
INSERT INTO public.teachers (teacher_id, first_name, last_name, father_name, date_of_birth, info) VALUES (3, 'FN_2', 'LN_2', 'FthN_2', '1980-03-01', 'some teacher');
INSERT INTO public.teachers (teacher_id, first_name, last_name, father_name, date_of_birth, info) VALUES (4, 'FN_3', 'LN_3', 'FthN_3', '1980-04-01', 'some teacher');
INSERT INTO public.teachers (teacher_id, first_name, last_name, father_name, date_of_birth, info) VALUES (5, 'FN_4', 'LN_4', 'FthN_4', '1980-05-01', 'some teacher');
INSERT INTO public.teachers (teacher_id, first_name, last_name, father_name, date_of_birth, info) VALUES (6, 'Vasyl', 'Matsenko', 'Hryhorii', '1954-01-01', 'MM teacher');
INSERT INTO public.teachers (teacher_id, first_name, last_name, father_name, date_of_birth, info) VALUES (7, 'Skutar', 'Igor', 'Dmytro', '1979-06-24', 'PHP, Python');


--
-- TOC entry 2202 (class 0 OID 0)
-- Dependencies: 190
-- Name: teachers_teacher_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.teachers_teacher_id_seq', 7, true);


--
-- TOC entry 2035 (class 2606 OID 76541)
-- Name: auditories auditories_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.auditories
    ADD CONSTRAINT auditories_pkey PRIMARY KEY (auditory_id);


--
-- TOC entry 2043 (class 2606 OID 76570)
-- Name: groups groups_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups
    ADD CONSTRAINT groups_pkey PRIMARY KEY (group_id);


--
-- TOC entry 2045 (class 2606 OID 76584)
-- Name: lessons kauditory; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lessons
    ADD CONSTRAINT kauditory UNIQUE (lesson_time_id, day_of_week_id, auditory_id);


--
-- TOC entry 2047 (class 2606 OID 76580)
-- Name: lessons kgroup; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lessons
    ADD CONSTRAINT kgroup UNIQUE (lesson_time_id, day_of_week_id, group_id);


--
-- TOC entry 2049 (class 2606 OID 76582)
-- Name: lessons kteacher; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lessons
    ADD CONSTRAINT kteacher UNIQUE (lesson_time_id, day_of_week_id, teacher_id);


--
-- TOC entry 2051 (class 2606 OID 76578)
-- Name: lessons lessons_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lessons
    ADD CONSTRAINT lessons_pkey PRIMARY KEY (lesson_id);


--
-- TOC entry 2039 (class 2606 OID 76554)
-- Name: lessons_time lessons_time_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lessons_time
    ADD CONSTRAINT lessons_time_pkey PRIMARY KEY (lesson_number);


--
-- TOC entry 2037 (class 2606 OID 76549)
-- Name: subjects subjects_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subjects
    ADD CONSTRAINT subjects_pkey PRIMARY KEY (subject_id);


--
-- TOC entry 2041 (class 2606 OID 76562)
-- Name: teachers teachers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.teachers
    ADD CONSTRAINT teachers_pkey PRIMARY KEY (teacher_id);


--
-- TOC entry 2055 (class 2606 OID 76600)
-- Name: lessons lessons_auditory_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lessons
    ADD CONSTRAINT lessons_auditory_id_fkey FOREIGN KEY (auditory_id) REFERENCES public.auditories(auditory_id);


--
-- TOC entry 2053 (class 2606 OID 76590)
-- Name: lessons lessons_group_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lessons
    ADD CONSTRAINT lessons_group_id_fkey FOREIGN KEY (group_id) REFERENCES public.groups(group_id);


--
-- TOC entry 2052 (class 2606 OID 76585)
-- Name: lessons lessons_lesson_time_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lessons
    ADD CONSTRAINT lessons_lesson_time_id_fkey FOREIGN KEY (lesson_time_id) REFERENCES public.lessons_time(lesson_number);


--
-- TOC entry 2056 (class 2606 OID 76605)
-- Name: lessons lessons_subject_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lessons
    ADD CONSTRAINT lessons_subject_id_fkey FOREIGN KEY (subject_id) REFERENCES public.subjects(subject_id);


--
-- TOC entry 2054 (class 2606 OID 76595)
-- Name: lessons lessons_teacher_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lessons
    ADD CONSTRAINT lessons_teacher_id_fkey FOREIGN KEY (teacher_id) REFERENCES public.teachers(teacher_id);


-- Completed on 2019-11-19 17:46:16

--
-- PostgreSQL database dump complete
--

