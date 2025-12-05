package com.financialapp.service;

import com.financialapp.model.Loan;
import org.springframework.stereotype.Service;

/**
 * Loan Calculator Service - Demonstrates Abstraction and core logic.
 */
@Service
public class LoanCalculator {

    // Abstraction: Hides complex calculation details from the controller/user.
    public double calculateMonthlyPayment(Loan loan) {
        
        // Conditional: Check for invalid zero/negative rates (though validation handles most of this)
        if (loan.getAnnualRate() <= 0) {
            // Simple return if interest is zero (though real loans usually have fees)
            return loan.getPrincipal() / (loan.getTermYears() * 12.0); 
        }

        double monthlyRate = (loan.getAnnualRate() / 100.0) / 12.0;
        int numberOfPayments = loan.getTermYears() * 12; // Loops: The count for iteration

        double principal = loan.getPrincipal();

        // Standard Loan Payment Formula
        // M = P [ i(1 + i)^n ] / [ (1 + i)^n – 1]
        
        double numerator = monthlyRate * Math.pow(1 + monthlyRate, numberOfPayments);
        double denominator = Math.pow(1 + monthlyRate, numberOfPayments) - 1;

        double monthlyPayment = principal * (numerator / denominator);
        
        // This method could be expanded with a loop to calculate and return an Amortization Table (Array/List)
        
        return monthlyPayment;
    }
}