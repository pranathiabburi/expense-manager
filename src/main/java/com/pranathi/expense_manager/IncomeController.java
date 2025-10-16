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
    @PutMapping("/{id}")
    public Income updateIncome(@PathVariable Long id, @RequestBody Income updatedIncome) {
        return repository.findById(id)
                .map(income -> {
                    income.setSource(updatedIncome.getSource());
                    income.setAmount(updatedIncome.getAmount());
                    return repository.save(income);
                })
                .orElseThrow(() -> new RuntimeException("Income not found with id " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteIncome(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Income not found with id " + id);
        }
        repository.deleteById(id);
    }

}
