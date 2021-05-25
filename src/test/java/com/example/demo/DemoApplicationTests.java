package com.example.demo;

import com.example.demo.src.user.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
@Transactional
@Rollback(value = false)
class DemoApplicationTests {

    @PersistenceContext
    EntityManager em;

//    @Test
//    void contextLoads() {
//
//        User userA = new User();
//        userA.setEmail("aowert@naver.com");
//
//        em.persist(userA);
//    }

}
