package com.study.genre.repository.jdbc;

import com.study.genre.model.Genre;
import com.study.genre.repository.GenreRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Repository("cachedGenreRepository")
public class CachedGenreRepository implements GenreRepository {

    private final Set<Genre> genreCache = new CopyOnWriteArraySet<>();
    @Value("${db.cache.renew.minutes}")
    private int renewTime;
    @Autowired
    private GenreRepository jdbcGenreRepository;

    public CachedGenreRepository() {

    }

    @PostConstruct
    private void initUpdater() {
        new Thread(this::updateCache).start();
    }

    @Override
    public List<Genre> findAllByMovieId(Integer movieId) {
        return jdbcGenreRepository.findAllByMovieId(movieId);
    }

    @Override
    public List<Genre> findAll() {
        return new ArrayList<>(genreCache);
    }

    @SneakyThrows
    private void updateCache() {
        while (true) {
            genreCache.addAll(jdbcGenreRepository.findAll());
            Thread.sleep((long) renewTime * 60 * 1000);
        }
    }
}
