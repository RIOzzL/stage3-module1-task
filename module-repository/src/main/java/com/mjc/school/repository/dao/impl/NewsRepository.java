package com.mjc.school.repository.dao.impl;

import com.mjc.school.repository.dao.Repository;
import com.mjc.school.repository.models.NewsModel;
import com.mjc.school.repository.utils.DataSource;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Setter
@Getter
public class NewsRepository implements Repository<NewsModel> {

    private final DataSource dataSource = DataSource.getInstance();

    @Override
    public List<NewsModel> readAll() {
        return dataSource.getNewsModelList();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        return dataSource.getNewsModelList().stream()
                .filter(newsModel -> newsModel.getId().equals(id))
                .findFirst();
    }

    @Override
    public NewsModel create(NewsModel newsModel) {
        newsModel.setId(dataSource.increaseNewsId());
        dataSource.getNewsModelList().add(newsModel);
        return newsModel;
    }

    @Override
    public NewsModel update(NewsModel newsModel) {
        NewsModel newsModelToUpdate = this.readById(newsModel.getId()).get();
        newsModelToUpdate.setTitle(newsModel.getTitle());
        newsModelToUpdate.setContent(newsModel.getContent());
        newsModelToUpdate.setAuthorId(newsModel.getAuthorId());
        newsModelToUpdate.setLastUpdatedDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return newsModelToUpdate;
    }

    @Override
    public Boolean deleteById(Long id) {
        Optional<NewsModel> newsToDelete = dataSource.getNewsModelList().stream()
                .filter(newsModel -> newsModel.getId().equals(id))
                .findFirst();

        if (newsToDelete.isPresent()) {
            dataSource.getNewsModelList().remove(newsToDelete.get());
            return true;
        } else {
            return false;
        }
    }
}
