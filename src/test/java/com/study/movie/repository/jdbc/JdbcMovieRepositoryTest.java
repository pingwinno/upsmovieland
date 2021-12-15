package com.study.movie.repository.jdbc;


import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import com.study.configuration.RootConfig;
import com.study.movie.model.Order;
import com.study.movie.model.OrderCriteria;
import lombok.SneakyThrows;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Testcontainers
@DBRider
@ExtendWith(SpringExtension.class)
@DBUnit(schema = "movie_store", caseSensitiveTableNames = true)
@ContextConfiguration(classes = {RootConfig.class})
public class JdbcMovieRepositoryTest {
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "secret";
    @Container
    private static PostgreSQLContainer<?> postgresqlContainer = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("movie_store")
            .withUsername(DB_USER)
            .withPassword(DB_PASSWORD);
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcMovieRepository movieRepository;
    private Flyway flyway;


    @DynamicPropertySource
    static void setDynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("db.url", () -> postgresqlContainer.getJdbcUrl());
        registry.add("db.username", () -> DB_USER);
        registry.add("db.password", () -> DB_PASSWORD);
        registry.add("db.driver.class", () -> postgresqlContainer.getDriverClassName());
    }

    @SneakyThrows
    @BeforeEach
    void init() {
        flyway = Flyway.configure()
                       .dataSource(dataSource)
                       .load();
        flyway.migrate();
    }

    @AfterEach
    void clean() {
        flyway.clean();
    }

    @Test
    @DataSet(value = "datasets/movie_without_description.yml")
    void givenMovieEntity_whenFindAll_thenReturnMovieEntity() {
        var movies = movieRepository.findAll();
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

    @Test
    @DataSet(value = "datasets/movie_without_description.yml")
    void givenMovieEntity_whenFindRandom_thenReturnMovieEntity() {
        var movies = movieRepository.findRandom(1);
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

    @Test
    @DataSet(value = "datasets/movies_with_genre.yml")
    void givenGenreId_whenFindByGenreId_thenReturnMovieEntity() {
        var movies = movieRepository.findByGenreId(1);
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

    @Test
    @DataSet(value = "datasets/two_movies_without_description.yml")
    void givenOrderByPriceDesc_whenFindAllAndOrderBy_thenReturnMovieEntitiesOrderedByPriceDesc() {
        var orderByPriceDesc = OrderCriteria.builder()
                                            .column("price")
                                            .order(Order.DESC)
                                            .build();
        var movies = movieRepository.findAllAndOrderBy(orderByPriceDesc);
        assertFalse(movies.isEmpty());
        var firstMovie = movies.get(0);
        assertEquals(2, firstMovie.getId());
        assertEquals("Прибытие поезда на вокзал Ла-Сьота2", firstMovie.getNameRussian());
        assertEquals("The Arrival of a Train2", firstMovie.getNameNative());
        assertEquals(1897, firstMovie.getYearOfRelease());
        assertEquals(9.9, firstMovie.getRating());
        assertEquals(29.99, firstMovie.getPrice());
        assertEquals("http://link.com2", firstMovie.getPicturePath());
        var secondMovie = movies.get(1);
        assertEquals(1, secondMovie.getId());
        assertEquals("Прибытие поезда на вокзал Ла-Сьота", secondMovie.getNameRussian());
        assertEquals("The Arrival of a Train", secondMovie.getNameNative());
        assertEquals(1896, secondMovie.getYearOfRelease());
        assertEquals(8.9, secondMovie.getRating());
        assertEquals(19.99, secondMovie.getPrice());
        assertEquals("http://link.com", secondMovie.getPicturePath());
    }

    @Test
    @DataSet(value = "datasets/two_movies_without_description.yml")
    void givenOrderByPriceAsc_whenFindAllAndOrderBy_thenReturnMovieEntitiesOrderedByPriceAsc() {
        var orderByPriceDesc = OrderCriteria.builder()
                                            .column("price")
                                            .order(Order.ASK)
                                            .build();
        var movies = movieRepository.findAllAndOrderBy(orderByPriceDesc);
        assertFalse(movies.isEmpty());
        var firstMovie = movies.get(0);
        assertEquals(1, firstMovie.getId());
        assertEquals("Прибытие поезда на вокзал Ла-Сьота", firstMovie.getNameRussian());
        assertEquals("The Arrival of a Train", firstMovie.getNameNative());
        assertEquals(1896, firstMovie.getYearOfRelease());
        assertEquals(8.9, firstMovie.getRating());
        assertEquals(19.99, firstMovie.getPrice());
        assertEquals("http://link.com", firstMovie.getPicturePath());
        var secondMovie = movies.get(1);
        assertEquals(2, secondMovie.getId());
        assertEquals("Прибытие поезда на вокзал Ла-Сьота2", secondMovie.getNameRussian());
        assertEquals("The Arrival of a Train2", secondMovie.getNameNative());
        assertEquals(1897, secondMovie.getYearOfRelease());
        assertEquals(9.9, secondMovie.getRating());
        assertEquals(29.99, secondMovie.getPrice());
        assertEquals("http://link.com2", secondMovie.getPicturePath());
    }

    @Test
    @DataSet(value = "datasets/two_movies_without_description.yml")
    void givenOrderByRatingDesc_whenFindAllAndOrderBy_thenReturnMovieEntitiesOrderedByRatingDesc() {
        var orderByPriceDesc = OrderCriteria.builder()
                                            .column("rating")
                                            .order(Order.DESC)
                                            .build();
        var movies = movieRepository.findAllAndOrderBy(orderByPriceDesc);
        assertFalse(movies.isEmpty());
        var firstMovie = movies.get(0);
        assertEquals(2, firstMovie.getId());
        assertEquals("Прибытие поезда на вокзал Ла-Сьота2", firstMovie.getNameRussian());
        assertEquals("The Arrival of a Train2", firstMovie.getNameNative());
        assertEquals(1897, firstMovie.getYearOfRelease());
        assertEquals(9.9, firstMovie.getRating());
        assertEquals(29.99, firstMovie.getPrice());
        assertEquals("http://link.com2", firstMovie.getPicturePath());
        var secondMovie = movies.get(1);
        assertEquals(1, secondMovie.getId());
        assertEquals("Прибытие поезда на вокзал Ла-Сьота", secondMovie.getNameRussian());
        assertEquals("The Arrival of a Train", secondMovie.getNameNative());
        assertEquals(1896, secondMovie.getYearOfRelease());
        assertEquals(8.9, secondMovie.getRating());
        assertEquals(19.99, secondMovie.getPrice());
        assertEquals("http://link.com", secondMovie.getPicturePath());
    }
}