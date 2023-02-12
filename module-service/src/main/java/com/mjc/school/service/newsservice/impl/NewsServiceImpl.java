package com.mjc.school.service.newsservice.impl;

import com.mjc.school.repository.dao.impl.NewsRepository;
import com.mjc.school.repository.factory.impl.NewsRepositoryFactory;
import com.mjc.school.repository.models.NewsModel;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.exceptiion.ValidatorException;
import com.mjc.school.service.newsservice.Service;
import com.mjc.school.service.utils.ObjectMapperUtils;
import com.mjc.school.service.validator.NewsValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static com.mjc.school.service.exceptiion.ServiceError.NEWS_ID_DOES_NOT_EXIST;

public class NewsServiceImpl implements Service<NewsDto> {

    private final NewsRepository newsRepository = NewsRepositoryFactory.getRepositoryFactory().getNewsRepository();
    private NewsValidator validator = NewsValidator.getInstance();

    @Override
    public List<NewsDto> getAll() {
        List<NewsModel> all = newsRepository.readAll();
        return ObjectMapperUtils.mapAll(all, NewsDto.class);
    }

    @Override
    public NewsDto getById(long id) {
        validator.validateById(id);
        Optional<NewsModel> byId = newsRepository.readById(id);
        return byId.map(newsModel -> ObjectMapperUtils.map(newsModel, NewsDto.class)).orElse(null);
    }

    @Override
    public NewsDto create(NewsDto newsDto) {
        validator.validateCreatedNews(newsDto);
        NewsModel newsModelToCreate = ObjectMapperUtils.map(newsDto, NewsModel.class);
        newsModelToCreate.setCreateDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        newsModelToCreate.setLastUpdatedDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return ObjectMapperUtils.map(newsRepository.create(newsModelToCreate), NewsDto.class);
    }

    @Override
    public NewsDto update(NewsDto newsDto) {
        Optional<NewsModel> byId = newsRepository.readById(newsDto.getId());
        if (byId.isPresent()) {
            validator.validateCreatedNews(newsDto);
            NewsModel updatedNewsModel = ObjectMapperUtils.map(newsDto, NewsModel.class);
            NewsModel newsModel = newsRepository.update(updatedNewsModel);
            return ObjectMapperUtils.map(newsModel, NewsDto.class);
        } else {
            throw new ValidatorException(String.format(NEWS_ID_DOES_NOT_EXIST.getMessage(), newsDto.getId()));
        }
    }

    @Override
    public boolean deleteById(long id) {
        Optional<NewsModel> byId = newsRepository.readById(id);
        if (byId.isPresent()) {
            return newsRepository.deleteById(id);
        } else {
            throw new ValidatorException(String.format(NEWS_ID_DOES_NOT_EXIST.getMessage(), id));
        }
    }
}