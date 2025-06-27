package com.btamis.directoryservice.unit;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/units")
@RequiredArgsConstructor
public class UnitController {
    // Adding test git change
    private final UnitRepository unitRepo;

    @GetMapping
    public List<Unit> getAllUnits() {
        return unitRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Unit> getUnitById(@PathVariable UUID id) {
        return unitRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Unit createUnit(@RequestBody Unit unit) {
        return unitRepo.save(unit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Unit> updateUnit(
            @PathVariable UUID id,
            @RequestBody Unit details) {
        return unitRepo.findById(id)
                .map(u -> {
                    u.setName(details.getName());
                    u.setDescription(details.getDescription());
                    u.setParent(details.getParent());
                    return ResponseEntity.ok(unitRepo.save(u));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUnit(@PathVariable UUID id) {
        return unitRepo.findById(id)
                .map(u -> {
                    unitRepo.delete(u);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}