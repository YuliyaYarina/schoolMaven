package ru.hogwarts.school.expenses;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExpensesByCategoryController {

    private final ExpenseService expenseService;

    public ExpensesByCategoryController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpense(@RequestParam("page") Integer pageNumber, @RequestParam("size") Integer pageSize) {
        List<Expense> expenses = expenseService.getAllExpense(pageNumber, pageSize);
        return ResponseEntity.ok(expenses);
    }

}
