package com.pranathi.expense_manager;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/income")
public class IncomeController {
    private final IncomeRepository repository;

    public IncomeController(IncomeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Income> getAllIncome() {
        return repository.findAll();
    }

    @PostMapping
    public Income addIncome(@RequestBody Income income) {
        return repository.save(income);
    }
}
