package com.study.country.repository.jdbc;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import com.study.TestConfig;
import com.study.configuration.RootConfig;
import lombok.SneakyThrows;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Testcontainers
@DBRider
@ExtendWith(SpringExtension.class)
@DBUnit(caseSensitiveTableNames = true)
@ContextConfiguration(classes = {TestConfig.class})
class JdbcCountryRepositoryTest {
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "secret";
    private static final PostgreSQLContainer<?> postgresqlContainer = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("movie_store")
            .withUsername(DB_USER)
            .withPassword(DB_PASSWORD)
            .withReuse(true);
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcCountryRepository countryRepository;
    private Flyway flyway;

    @BeforeAll
    static void start() {
        postgresqlContainer.start();
    }

    @DynamicPropertySource
    static void setDynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("db.url", postgresqlContainer::getJdbcUrl);
        registry.add("db.username", () -> DB_USER);
        registry.add("db.password", () -> DB_PASSWORD);
        registry.add("db.driver.class", postgresqlContainer::getDriverClassName);

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
    @DataSet(value = "datasets/country_entity.yml")
    void givenGenreEntity_whenFindAll_thenReturnGenreEntity() {
        var countries = countryRepository.findAll();
        assertFalse(countries.isEmpty());
        var genreObject = countries.get(0);
        assertEquals(1, genreObject.getId());
        assertEquals("США", genreObject.getName());
    }

    @Test
    @DataSet(value = "datasets/movies_with_genre_and_country.yml")
    void givenGenreEntity_whenFindAllByMovieId_thenReturnGenreEntity() {
        var countries = countryRepository.findAllByMovieId(1);
        assertFalse(countries.isEmpty());
        var genreObject = countries.get(0);
        assertEquals(1, genreObject.getId());
        assertEquals("США", genreObject.getName());
    }
}