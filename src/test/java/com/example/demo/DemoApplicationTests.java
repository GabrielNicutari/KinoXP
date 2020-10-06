package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.controller.Home;
import com.example.demo.model.Actor;
import com.example.demo.repository.ActorRepo;
import org.junit.jupiter.api.Test;

import org.mockito.internal.matchers.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManagerFactory;

@SpringBootTest
class SmokeTest {


    @Autowired
    private Home controller;
    @Autowired
    private ActorRepo actorRepo;
    @Autowired
    private Actor actor;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void whenFindByName_thenReturnEmployee() {
        // given
/*
        TestEntityManager entityManager = null;
        entityManager.persist(alex);
        entityManager.flush();
*/
        // when
        Actor found = actorRepo.findByName("Tim Roth");

        // then
        assertEquals("Tim Roth", found.getName());

    }
    @Test
    public void testActorClass()
    {
        actor.setActor_id(3);
    assertEquals(3,actor);
    }
}