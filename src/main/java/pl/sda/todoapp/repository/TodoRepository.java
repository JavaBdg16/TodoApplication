package pl.sda.todoapp.repository;

import pl.sda.todoapp.model.Todo;

public interface TodoRepository {
    Todo findOne(long id);

    Iterable<Todo> findAll();

    void save(Todo todo);

    void delete(Todo todo);
}
