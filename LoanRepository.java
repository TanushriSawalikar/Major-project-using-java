package com.financialapp.repository;

import com.financialapp.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Loan Repository - Demonstrates Inheritance and CRUD operations.
 */
// Inheritance: Inherits all methods (save, findAll, findById, delete) from JpaRepository.
// Collections: Treats the database table like a collection of Loan objects.
public interface LoanRepository extends JpaRepository<Loan, Long> {
    
    // Spring Data JPA automatically provides methods for CRUD operations.
    // You can add custom methods here if needed (e.g., findByPrincipalGreaterThan).
    
}