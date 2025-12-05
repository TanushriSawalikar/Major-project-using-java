package com.financialapp.controller;

import com.financialapp.model.Loan;
import com.financialapp.repository.LoanRepository;
import com.financialapp.service.LoanCalculator;
// Removed unused import: import com.financialapp.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Loan REST Controller - Simple RESTful API and Polymorphism.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanRepository loanRepository;
    private final LoanCalculator calculator;
    // Removed: private LoanService loanService; // Line 22 error fixed by removal

    // Spring handles dependency injection (Constructor Injection)
    // Constructor updated: removed the LoanService parameter since it's not being used for calculation/save
    public LoanController(LoanRepository loanRepository, LoanCalculator calculator) {
        this.loanRepository = loanRepository;
        this.calculator = calculator;
    }

    // 1. CREATE Loan (POST) - CRUD Operation
    @PostMapping
    public ResponseEntity<Loan> createLoan(@Valid @RequestBody Loan loan) {
        // ... (Existing calculation logic remains the same)
        double payment = calculator.calculateMonthlyPayment(loan);
        loan.setMonthlyPayment(Math.round(payment * 100.0) / 100.0);

        // Save to DB (CRUD - Create)
        Loan savedLoan = loanRepository.save(loan);
        return new ResponseEntity<>(savedLoan, HttpStatus.CREATED);
    }

    // 2. READ All Loans (GET) - CRUD Operation
    @GetMapping
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    // 3. READ Loan by ID (GET) - CRUD Operation
    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found with ID: " + id));
        return ResponseEntity.ok(loan);
    }
    
    // 4. DELETE (Delete Loan by ID) - CRUD Operation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
        // Fix for Line 61: Call the deleteById method directly from the injected LoanRepository.
        loanRepository.deleteById(id);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}