package com.btamis.directoryservice.unit;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class UnitResolvers {
    private final UnitRepository unitRepo;
    public UnitResolvers(UnitRepository unitRepo) {
        this.unitRepo = unitRepo;
    }

    @QueryMapping
    public List<Unit> units() {
        return unitRepo.findAll();
    }

    @QueryMapping
    public Unit unitById(@Argument UUID id) {
        return unitRepo.findById(id).orElse(null);
    }

    @SchemaMapping(typeName = "Unit", field = "parent")
    public Unit parent(Unit unit) {
        return unit.getParent();
    }
}