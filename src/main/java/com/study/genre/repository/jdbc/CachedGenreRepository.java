package com.study.genre.repository.jdbc;

import com.study.genre.model.Genre;
import com.study.genre.repository.GenreRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Repository("cachedGenreRepository")
public class CachedGenreRepository implements GenreRepository {

    @Value("${db.cache.renew.minutes}")
    private int renewTime;
    @Autowired
    private GenreRepository jdbcGenreRepository;
    private Set<Genre> genreCache = new CopyOnWriteArraySet<>();

    public CachedGenreRepository() {
        new Thread(() -> updateCache()).start();
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
