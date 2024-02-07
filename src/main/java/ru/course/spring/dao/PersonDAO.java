package ru.course.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.course.spring.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }



    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);

    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, age, email) VALUES(?,?,?)", person.getName(), person.getAge(), person.getEmail());

    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? WHERE id=?", person.getName(), person.getAge(), person.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);

    }

///////////////////////
// test batch update//
/////////////////////
    private List<Person> create1000people (){
    List<Person> people = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            people.add(new Person(i, "name"+i, 30, "mail"+i+"@mail.com") );
        }
        return people;
    }
    //almost 14s
    public void testMultipleUpdate(){
    List<Person> people = create1000people();
    long before =System.currentTimeMillis();
    for (Person person: people){
        jdbcTemplate.update("INSERT INTO Person VALUES(1,?,?,?)", person.getName(), person.getAge(), person.getEmail());
    }

    long after =System.currentTimeMillis();
        System.out.println((after-before) +" -usual update took time");
    }
    //0.2s
    public void testBatchUpdate(){
        List<Person> people = create1000people();
        long before =System.currentTimeMillis();
            jdbcTemplate.batchUpdate("INSERT INTO Person VALUES(?,?,?,?)",
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            ps.setInt(1, people.get(i).getId());
                            ps.setString(2, people.get(i).getName());
                            ps.setInt(3, people.get(i).getAge());
                            ps.setString(4, people.get(i).getEmail());
                        }

                        @Override
                        public int getBatchSize() {
                            return people.size();
                        }
                    });

        long after =System.currentTimeMillis();
        System.out.println((after-before) +" -batch update took time");

    }
}
//create table Person(
//        id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
//        name varchar NOT NULL,
//        age int check(age > 0),
//email varchar UNIQUE
//
//)