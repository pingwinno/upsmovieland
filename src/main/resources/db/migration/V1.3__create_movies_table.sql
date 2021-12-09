create table MOVIES
(
    ID           SERIAL PRIMARY KEY,
    NAME_RUSSIAN VARCHAR(255) NOT NULL,
    NAME_NATIVE  VARCHAR(255) NOT NULL,
    RELEASE_DATE INT          NOT NULL,
    DESCRIPTION  VARCHAR      NOT NULL,
    RATING       DECIMAL      NOT NULL,
    PRICE        DECIMAL      NOT NULL,
    POSTER_LINK  VARCHAR      NOT NULL
);