--
-- File generated with SQLiteStudio v3.1.1 on Wed Apr 11 11:48:47 2018
--
-- Text encoding used: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: categories
CREATE TABLE categories (
    id   VARCHAR (255) PRIMARY KEY
                       NOT NULL,
    name VARCHAR (255) NOT NULL
);


-- Table: course
CREATE TABLE course (

    id         VARCHAR (255) PRIMARY KEY
                             NOT NULL,
    code       VARCHAR (255) NOT NULL,
    title      VARCHAR (255) NOT NULL,
    short_name VARCHAR (255) NOT NULL,
    category   VARCHAR (255) NOT NULL
                             REFERENCES categories (id),
    credits    INTEGER       NOT NULL
                             CHECK (credits IN (2, 4, 8) ) 
                             DEFAULT (2) 
);


-- Table: course_delivery
CREATE TABLE course_delivery (
    id          VARCHAR (255) PRIMARY KEY
                              NOT NULL,
    course_id   VARCHAR (255) REFERENCES course (id) 
                              NOT NULL,
    lecturer_id VARCHAR (255) REFERENCES lecturer (id) 
                              NOT NULL,
    semester    VARCHAR (255) NOT NULL,
    year        INTEGER       NOT NULL
                              CHECK (year BETWEEN 1900 AND 2100) 
);


-- Table: delivery_specs
CREATE TABLE delivery_specs (
    id           VARCHAR (255) PRIMARY KEY
                               NOT NULL,
    delivery_id  VARCHAR (255) REFERENCES course_delivery (id) 
                               NOT NULL,
    time_slot_id VARCHAR (255) REFERENCES time_slot (id) 
                               NOT NULL,
    room_id      VARCHAR (255) REFERENCES room (id) 
);


-- Table: lecturer
CREATE TABLE lecturer (
    id         VARCHAR (255) PRIMARY KEY
                             NOT NULL,
    name       VARCHAR (255) NOT NULL,
    title      VARCHAR (255) NOT NULL,
    laboratory VARCHAR (255) NOT NULL
);


-- Table: room
CREATE TABLE room (
    id       VARCHAR (255) PRIMARY KEY
                           NOT NULL,
    capacity INTEGER       NOT NULL
                           DEFAULT (0) 
);


-- Table: student
CREATE TABLE student (
    id          VARCHAR (255) PRIMARY KEY
                              NOT NULL,
    name        VARCHAR (255) NOT NULL,
    nationality VARCHAR (255) NOT NULL,
    gpa         REAL          DEFAULT (0) 
                              CHECK (gpa BETWEEN 0 AND 4) 
                              NOT NULL
);


-- Table: supervisory
CREATE TABLE supervisory (
    id          VARCHAR (255) PRIMARY KEY
                              NOT NULL,
    lecturer_id VARCHAR (255) REFERENCES lecturer (id) 
                              NOT NULL,
    student_id  VARCHAR (255) REFERENCES student (id) 
                              NOT NULL,
    start_date  DATE          NOT NULL
                              DEFAULT (strftime('%Y-%m-%d', 'now') ),
    end_date    DATE          NOT NULL
                              DEFAULT (date('now', '+15 months') ) 
);


-- Table: time_slot
CREATE TABLE time_slot (
    id         VARCHAR (255) PRIMARY KEY
                             NOT NULL,
    start_time TIME          NOT NULL
                             DEFAULT ('09:20'),
    end_time   TIME          NOT NULL
                             DEFAULT ('10:50') 
);


COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
