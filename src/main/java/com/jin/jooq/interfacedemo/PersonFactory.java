package com.jin.jooq.interfacedemo;

public interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}
