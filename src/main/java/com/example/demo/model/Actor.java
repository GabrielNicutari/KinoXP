package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Actor {
    @Id
    private int actor_id;
    private String name;

    public Actor ()
    {
    }

    public Actor (int actor_id, String name)
    {
        this.actor_id = actor_id;
        this.name = name;
    }

    public int getActor_id ()
    {
        return actor_id;
    }

    public void setActor_id (int actor_id)
    {
        this.actor_id = actor_id;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString ()
    {
        return "Actor{" + "actor_id=" + actor_id + ", name='" + name + '\'' + '}';
    }
}
