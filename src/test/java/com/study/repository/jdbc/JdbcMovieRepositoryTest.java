package com.study.repository.jdbc;

import com.study.mapper.MovieMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Testcontainers
class JdbcMovieRepositoryTest {

    @Container
    private PostgreSQLContainer<?> postgresqlContainer = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("movieland")
            .withUsername("foo")
            .withPassword("secret");
    private JdbcMovieRepository movieRepository;
    private HikariDataSource dataSource;

    @BeforeEach
    void init() {
        var config = new HikariConfig();
        config.setUsername(postgresqlContainer.getUsername());
        config.setJdbcUrl(postgresqlContainer.getJdbcUrl());
        config.setDriverClassName(postgresqlContainer.getDriverClassName());
        config.setPassword(postgresqlContainer.getPassword());
        dataSource = new HikariDataSource(config);
        Flyway flyway = Flyway.configure()
                              .locations("/db/test")
                              .dataSource(dataSource)
                              .load();
        flyway.migrate();
        movieRepository = new JdbcMovieRepository(dataSource, new MovieMapper());
    }

    @Test
    @FlywayTest
    void givenMovieEntity_whenGetAll_thenReturnMovieEntity() {
        var movies = movieRepository.getAllMovies();
        assertFalse(movies.isEmpty());
        var movieObject = movies.get(0);
        assertEquals(1, movieObject.getId());
        assertEquals("Прибытие поезда на вокзал Ла-Сьота", movieObject.getNameRussian());
        assertEquals("The Arrival of a Train", movieObject.getNameNative());
        assertEquals(1896, movieObject.getYearOfRelease());
        assertEquals(9.9, movieObject.getRating());
        assertEquals(19.99, movieObject.getPrice());
        assertEquals("http://link.com", movieObject.getPicturePath());
    }
}