package com.mjc.school.service.newsservice.impl;

import com.mjc.school.repository.dao.impl.NewsRepository;
import com.mjc.school.repository.factory.impl.NewsRepositoryFactory;
import com.mjc.school.repository.models.News;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.exceptiion.ValidatorException;
import com.mjc.school.service.newsservice.Service;
import com.mjc.school.service.utils.ObjectMapperUtils;
import com.mjc.school.service.validator.NewsValidator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.mjc.school.service.exceptiion.ServiceError.NEWS_ID_DOES_NOT_EXIST;

public class NewsServiceImpl implements Service<NewsDto> {

    private final NewsRepository newsRepository = NewsRepositoryFactory.getRepositoryFactory().getNewsRepository();
    private NewsValidator validator = NewsValidator.getInstance();

    @Override
    public List<NewsDto> getAll() {
        List<News> all = newsRepository.getAll();
        return ObjectMapperUtils.mapAll(all, NewsDto.class);
    }

    @Override
    public NewsDto getById(long id) {
        validator.validateById(id);
        Optional<News> byId = newsRepository.getById(id);
        return byId.map(news -> ObjectMapperUtils.map(news, NewsDto.class)).orElse(null);
    }

    @Override
    public NewsDto create(NewsDto newsDto) {
        validator.validateCreatedNews(newsDto);
        News newsToCreate = ObjectMapperUtils.map(newsDto, News.class);
        newsToCreate.setCreateDate(LocalDateTime.now());
        newsToCreate.setLastUpdatedDate(LocalDateTime.now());
        return ObjectMapperUtils.map(newsRepository.save(newsToCreate), NewsDto.class);
    }

    @Override
    public NewsDto update(NewsDto newsDto) {
        Optional<News> byId = newsRepository.getById(newsDto.getId());
        if (byId.isPresent()) {
            validator.validateCreatedNews(newsDto);
            News news = byId.get();
            news.setTitle(newsDto.getTitle());
            news.setContent(newsDto.getContent());
            news.setAuthorId(newsDto.getAuthorId());
            news.setLastUpdatedDate(LocalDateTime.now());
            return ObjectMapperUtils.map(news, NewsDto.class);
        } else {
            throw new ValidatorException(String.format(NEWS_ID_DOES_NOT_EXIST.getMessage(), newsDto.getId()));
        }
    }

    @Override
    public boolean deleteById(long id) {
        Optional<News> byId = newsRepository.getById(id);
        if (byId.isPresent()) {
            return newsRepository.deleteById(id);
        } else {
            throw new ValidatorException(String.format(NEWS_ID_DOES_NOT_EXIST.getMessage(), id));
        }
    }
}