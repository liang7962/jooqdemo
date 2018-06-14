package com.jin.jooq.service;

import com.jin.jooq.tables.tables.pojos.Register;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import static com.jin.jooq.tables.Tables.REGISTER;

@Service
public class DemoServiceImpl implements DemoService{

    @Autowired
    private DSLContext create;

    @Override
    public List<Register> findAll() {
        List<Register> list=create.select().from(REGISTER).fetchInto(Register.class);
        return list;
    }

    @Override
    public Boolean save(Register register) {
        create.insertInto(REGISTER,REGISTER.REGISTERNO,REGISTER.ADDRESS,REGISTER.AGE,REGISTER.REGISTERNAME,REGISTER.ROLE,REGISTER.STATUS)
                .values(register.getRegisterno(),register.getAddress(),register.getAge(),register.getRegistername(),register.getRole(),register.getStatus())
                .execute();
        return true;
    }

    @Override
    public Boolean update(Register register) {
        create.update(REGISTER).set(REGISTER.ADDRESS,register.getAddress()).set(REGISTER.REGISTERNAME,register.getRegistername())
                .where(REGISTER.REGISTERNO.eq(register.getRegisterno())).execute();
        return true;
    }

    @Override
    public Register findOne(Register register) {
        List<Register> list = create.selectFrom(REGISTER).where(REGISTER.REGISTERNO.eq(register.getRegisterno())).fetchInto(Register.class);
        return list.get(0);
    }

    @Override
    public Boolean delectRegister(Register register) {
        create.deleteFrom(REGISTER).where(REGISTER.REGISTERNO.eq(register.getRegisterno())).execute();
        return true;
    }

    @Override
    public List<Register> findAllGroudByStatus(Register register) {
        List<Register> list = create.select().from(REGISTER).where(REGISTER.REGISTERNO.eq(register.getRegisterno())).groupBy(REGISTER.STATUS).fetchInto(Register.class);
        return list;
    }


}
