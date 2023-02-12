package com.mjc.school.repository.dao.impl;

import com.mjc.school.repository.dao.Repository;
import com.mjc.school.repository.models.News;
import com.mjc.school.repository.utils.DataSource;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Setter
@Getter
public class NewsRepository implements Repository<News> {

    private final DataSource dataSource = DataSource.getInstance();

    @Override
    public List<News> readAll() {
        return dataSource.getNewsList();
    }

    @Override
    public Optional<News> readById(Long id) {
        return dataSource.getNewsList().stream()
                .filter(news -> news.getId().equals(id))
                .findFirst();
    }

    @Override
    public News create(News news) {
        news.setId(dataSource.increaseNewsId());
        dataSource.getNewsList().add(news);
        return news;
    }

    @Override
    public News update(News news) {
        News newsToUpdate = this.readById(news.getId()).get();
        newsToUpdate.setTitle(news.getTitle());
        newsToUpdate.setContent(news.getContent());
        newsToUpdate.setAuthorId(news.getAuthorId());
        newsToUpdate.setLastUpdatedDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return newsToUpdate;
    }

    @Override
    public Boolean deleteById(Long id) {
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
