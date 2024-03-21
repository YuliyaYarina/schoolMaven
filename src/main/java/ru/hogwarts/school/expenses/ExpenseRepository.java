package ru.hogwarts.school.expenses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    @Query(value = "SELECT category, SUM(amount) as amount FROM expenses GROUP BY category", nativeQuery = true)
    List<ExpensesByCategory> getExpensesByCategory();
}
