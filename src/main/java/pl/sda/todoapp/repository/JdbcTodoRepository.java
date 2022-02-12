package pl.sda.todoapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.sda.todoapp.model.Todo;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcTodoRepository implements TodoRepository {

    private JdbcTemplate jdbc;

    @Autowired // od wersji Spring 4.3 @Autowired nie jest wymagany
    public JdbcTodoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Todo findOne(long id) {
        return jdbc.queryForObject(
                "select * from todo where id = ?",
                this::mapRowToTodo,
                id);
    }

    @Override
    public Iterable<Todo> findAll() {
        return jdbc.query(
                "select * from todo",
                this::mapRowToTodo
        );
    }

    @Override
    public void save(Todo todo) {
        jdbc.update(
                "insert into todo (name, description, createDate) values (?, ?, ?)",
                todo.getName(),
                todo.getDescription(),
                todo.getCreateDate()
        );
    }

    @Override
    public void delete(Todo todo) {
        jdbc.update(
                "delete from todo where id = ?",
                todo.getId()
        );
    }

    private Todo mapRowToTodo(ResultSet rs, int rowNum) throws SQLException {
        return new Todo(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getDate("createDate")
        );
    }
}
