package ru.course.spring.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.course.spring.models.Book;

import java.util.List;


@Component
public class BookDao {
    private JdbcTemplate jdbcTemplate;

    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);

    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name, person_id) VALUES(?,?)", book.getName(), book.getPerson_id());

    }

    public void takeBook(int id, Book book) {
        jdbcTemplate.update("UPDATE Book SET person_id WHERE id=?", book.getPerson_id(), id);
    }
    public void returnBook(int id){
        jdbcTemplate.update("UPDATE Book SET person_id WHERE id=?", null, id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);

    }
}
