package com.mjc.school.service.newsservice;

import java.util.List;

public interface Service<T> {

    public List<T> getAll();

    public T getById(long id);

    public T create(T t);

    public T update(T t);

    public boolean deleteById(long id);


}
