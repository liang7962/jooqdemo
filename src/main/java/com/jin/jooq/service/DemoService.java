package com.jin.jooq.service;

import com.jin.jooq.tables.tables.pojos.Register;

import java.util.List;

public interface DemoService {
     List<Register> findAll();
     Boolean save(Register register);
     Boolean update(Register register);
     Register findOne(Register register);
     Boolean delectRegister(Register register);
     List<Register> findAllGroudByStatus(Register register);
}
