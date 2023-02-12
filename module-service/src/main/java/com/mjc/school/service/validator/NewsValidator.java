package com.mjc.school.service.validator;

import com.mjc.school.repository.dao.impl.NewsRepository;
import com.mjc.school.repository.factory.impl.NewsRepositoryFactory;
import com.mjc.school.repository.models.NewsModel;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.exceptiion.ValidatorException;

import java.util.Optional;

import static com.mjc.school.service.exceptiion.ServiceError.*;

public class NewsValidator {
    private static final String NEWS_CONTENT = "News content";
    private static final String NEWS_TITLE = "News title";
    private static final Integer NEWS_CONTENT_MIN_LENGTH = 5;
    private static final Integer NEWS_CONTENT_MAX_LENGTH = 255;
    private static final Integer NEWS_TITLE_MIN_LENGTH = 5;
    private static final Integer NEWS_TITLE_MAX_LENGTH = 30;

    private static NewsValidator newsValidator;
    private NewsRepository newsRepository;

    private NewsValidator() {
        newsRepository = NewsRepositoryFactory.getRepositoryFactory().getNewsRepository();
    }

    public static NewsValidator getInstance() {
        if (newsValidator == null) {
            newsValidator = new NewsValidator();
            return newsValidator;
        }
        return newsValidator;
    }

    public void validateById(long id) {
        Optional<NewsModel> news = newsRepository.readById(id);
        if (news.isEmpty()) {
            throw new ValidatorException(String.format(NEWS_ID_DOES_NOT_EXIST.getMessage(), id));
        }
    }

    public void validateCreatedNews(NewsDto newsDto) {
        StringBuilder errorMessage = new StringBuilder();
        if (newsDto.getTitle().length() < NEWS_TITLE_MIN_LENGTH || newsDto.getTitle().length() > NEWS_TITLE_MAX_LENGTH) {
            errorMessage.append(String.format(VALIDATE_STRING_LENGTH.getMessage(), NEWS_TITLE,
                    NEWS_TITLE_MIN_LENGTH, NEWS_TITLE_MAX_LENGTH, NEWS_TITLE, newsDto.getTitle())).append("\n");
        }
        if (newsDto.getContent().length() < NEWS_CONTENT_MIN_LENGTH || newsDto.getContent().length() > NEWS_CONTENT_MAX_LENGTH) {
            errorMessage.append(String.format(VALIDATE_STRING_LENGTH.getMessage(), NEWS_CONTENT,
                    NEWS_CONTENT_MIN_LENGTH, NEWS_CONTENT_MAX_LENGTH, NEWS_CONTENT, newsDto.getContent())).append("\n");
        }
        if (!newsRepository.readAll().stream().map(NewsModel::getAuthorId).toList().contains(newsDto.getAuthorId())) {
            errorMessage.append(String.format(AUTHOR_ID_DOES_NOT_EXIST.getMessage(), newsDto.getAuthorId())).append("\n");
        }
        if (!errorMessage.isEmpty()) {
            throw new ValidatorException(errorMessage.toString());
        }
    }
}