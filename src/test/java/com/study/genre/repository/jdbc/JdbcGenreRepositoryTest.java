package com.study.genre.repository.jdbc;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import com.study.configuration.RootConfig;
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
class JdbcGenreRepositoryTest {
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
    private JdbcGenreRepository genreRepository;
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
    @DataSet(value = "datasets/genre_entity.yml")
    void givenGenreEntity_whenFindAll_thenReturnGenreEntity() {
        var genres = genreRepository.findAll();
        assertFalse(genres.isEmpty());
        var genreObject = genres.get(0);
        assertEquals(1, genreObject.getId());
        assertEquals("genre1", genreObject.getName());
    }
}