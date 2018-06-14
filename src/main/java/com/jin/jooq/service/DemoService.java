package com.jin.jooq.service;

import com.jin.jooq.tables.tables.pojos.Register;

import java.util.List;

public interface DemoService {
    public List<Register> findAll();
    public Boolean save(Register register);
    public Boolean update(Register register);
    public Register findOne(Register register);
    public Boolean delectRegister(Register register);
}
