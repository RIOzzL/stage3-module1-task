package com.mjc.school.controller;

import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.factory.ServiceFactory;
import com.mjc.school.service.newsservice.Service;

import java.util.List;

public class NewsController {

    Service<NewsDto> newsService = ServiceFactory.getInstance().getNewsService();

    public List<NewsDto> getAllNews() {
        return newsService.getAll();
    }

    public NewsDto getNewsById(long id) {
        return newsService.getById(id);
    }

    public NewsDto createNews(NewsDto newsDto) {
        return newsService.create(newsDto);
    }

    public NewsDto updateNews(NewsDto newsDto) {
        return newsService.update(newsDto);
    }

    public boolean removeNews(long id) {
        return newsService.deleteById(id);
    }
}
