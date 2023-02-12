package com.mjc.school.repository.dao;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    public List<T> getAll();

    public Optional<T> getById(Long id);

    public T save(T t);

    public boolean deleteById(Long id);
}
