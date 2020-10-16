package com.example.demo.repository;

import com.example.demo.model.Actor;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Replace;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ActorRepoTest {

    @Autowired
    ActorRepo actorRepoTest;



    @Test
    void fetchAll() {
    List<Actor> actors=actorRepoTest.fetchAll();
    assertThat(actors).isNotEmpty();
    }

    @Test
    void findByName() {
        Actor temp= new Actor(6,"Tim Roth");
      assert(temp.equals(actorRepoTest.findByName("Tim Roth")));

    }
}