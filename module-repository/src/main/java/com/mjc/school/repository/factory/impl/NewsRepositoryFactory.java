package com.mjc.school.repository.factory.impl;

import com.mjc.school.repository.dao.impl.NewsRepository;

public class NewsRepositoryFactory {

    private static NewsRepositoryFactory newsRepositoryFactory;

    private NewsRepositoryFactory() {
    }

    public static NewsRepositoryFactory getRepositoryFactory() {
        if (newsRepositoryFactory == null) {
            newsRepositoryFactory = new NewsRepositoryFactory();
            return newsRepositoryFactory;
        }
        return newsRepositoryFactory;
    }

    public NewsRepository getNewsRepository() {
        return new NewsRepository();
    }
}
