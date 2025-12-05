package com.financialapp.service;

import com.financialapp.model.Loan;
import com.financialapp.repository.LoanRepository;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    // 1. Declare final dependency (best practice)
    private final LoanRepository loanRepository;

    // 2. Constructor Injection (Spring automatically injects LoanRepository bean)
    // Removed @Autowired annotation as it's optional for constructor injection since Spring 4.3
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    // --- 1. LOAN CALCULATION & SAVE LOGIC ---
    public Loan calculateAndSave(Loan loan) {
        // Assume the Loan model has the fields: principal, annualRate, termYears
        double principal = loan.getPrincipal();
        double annualRate = loan.getAnnualRate();
        int termYears = loan.getTermYears();

        double monthlyRate = annualRate / 100 / 12;
        int numPayments = termYears * 12;

        double monthlyPayment;

        if (annualRate == 0) {
            monthlyPayment = principal / numPayments;
        } else {
            monthlyPayment = principal * (monthlyRate * Math.pow(1 + monthlyRate, numPayments)) / 
                             (Math.pow(1 + monthlyRate, numPayments) - 1);
        }

        loan.setMonthlyPayment(monthlyPayment);
        return loanRepository.save(loan);
    }

    // --- 2. GET ALL LOANS LOGIC ---
    public Iterable<Loan> findAll() {
        return loanRepository.findAll();
    }
    
    // --- 3. NEW DELETE LOGIC ---
    public void delete(Long id) {
        // This is where the DELETE operation is executed
        loanRepository.deleteById(id);
    }
}