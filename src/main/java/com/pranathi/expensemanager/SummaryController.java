package com.pranathi.expensemanager;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SummaryController {
    private final ExpenseRepository expenseRepository;
    private final IncomeRepository incomeRepository;

    public SummaryController(ExpenseRepository expenseRepository, IncomeRepository incomeRepository) {
        this.expenseRepository = expenseRepository;
        this.incomeRepository = incomeRepository;
    }

    @GetMapping("/summary")
    public Map<String, Double> getSummary() {
        double totalExpense = expenseRepository.findAll().stream()
                .mapToDouble(Expense::getAmount).sum();
        double totalIncome = incomeRepository.findAll().stream()
                .mapToDouble(Income::getAmount).sum();

        Map<String, Double> summary = new HashMap<>();
        summary.put("totalIncome", totalIncome);
        summary.put("totalExpense", totalExpense);
        summary.put("balance", totalIncome - totalExpense);
        return summary;
    }
}
