package com.mjc.school.repository.dao.impl;

import com.mjc.school.repository.dao.Repository;
import com.mjc.school.repository.models.News;
import com.mjc.school.repository.utils.DataSource;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Setter
@Getter
public class NewsRepository implements Repository<News> {

    private final DataSource dataSource = DataSource.getInstance();

    @Override
    public List<News> getAll() {
        return dataSource.getNewsList();
    }

    @Override
    public Optional<News> getById(Long id) {
        return dataSource.getNewsList().stream()
                .filter(news -> news.getId().equals(id))
                .findFirst();
    }

    @Override
    public News save(News news) {
        news.setId(dataSource.increaseNewsId());
        dataSource.getNewsList().add(news);
        return news;
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<News> newsToDelete = dataSource.getNewsList().stream()
                .filter(news -> news.getId().equals(id))
                .findFirst();

        if (newsToDelete.isPresent()) {
            dataSource.getNewsList().remove(newsToDelete.get());
            return true;
        } else {
            return false;
        }
    }
}
