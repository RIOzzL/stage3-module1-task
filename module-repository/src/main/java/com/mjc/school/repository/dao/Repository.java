package com.mjc.school.repository.dao;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    public List<T> readAll();

    public Optional<T> readById(Long id);

    public T create(T t);

    public T update(T t);

    public Boolean deleteById(Long id);
}
