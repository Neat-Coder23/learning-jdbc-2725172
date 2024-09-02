package com.frankmoley.lil.data.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T, ID> {
  List<T> getAll();
  T create(T entity);
  Optional<T> getOne(ID id);
  T update(T entity);
  void delete(ID id);
}
