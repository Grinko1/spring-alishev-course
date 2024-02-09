package ru.course.spring.dao;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.course.spring.models.Person;

import java.util.List;

@Component
public class PersonDAO2 {
    private final EntityManager entityManager;

    @Autowired
    public PersonDAO2(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public void testNPlus1(){
        Session session = entityManager.unwrap(Session.class);
//        List<Person> people = session.createQuery("select p from Person p", Person.class).getResultList();
//        for (Person person: people){
//            System.out.println("Person has : "+ person.getItems());
//        }
        List<Person> people = session.createQuery("select p from Person p LEFT JOIN FETCH p.items").getResultList();
        System.out.println(people);
        for (Person person : people) {
            System.out.println("Person has : " + person.getItems());
        }

    }
}
