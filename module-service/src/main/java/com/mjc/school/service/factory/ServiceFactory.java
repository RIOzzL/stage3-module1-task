package com.mjc.school.service.factory;

import com.mjc.school.service.newsservice.impl.NewsServiceImpl;

public class ServiceFactory {

    private static ServiceFactory serviceFactory;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        if (serviceFactory == null) {
            serviceFactory = new ServiceFactory();
            return serviceFactory;
        }
        return serviceFactory;
    }

    public NewsServiceImpl getNewsService() {
        return new NewsServiceImpl();
    }
}
