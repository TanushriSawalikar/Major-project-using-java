package com.financialapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * Loan Entity - Demonstrates Encapsulation and Validation.
 */
@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Encapsulation: Fields are private, accessed via public getters/setters.
    // Validation: Ensures data integrity before saving/processing.
    @NotNull(message = "Principal cannot be null.")
    @Min(value = 100, message = "Principal must be at least 100.")
    private Double principal;

    @NotNull(message = "Rate cannot be null.")
    @DecimalMin(value = "0.01", message = "Rate must be positive.")
    private Double annualRate;

    @NotNull(message = "Term must be specified.")
    @Min(value = 1, message = "Term must be at least 1 year.")
    private Integer termYears;

    // The calculated result, stored in the DB
    private Double monthlyPayment;

    // Default Constructor (required by JPA)
    public Loan() {}

    // Getters and Setters (Demonstrating Encapsulation)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Double getPrincipal() { return principal; }
    public void setPrincipal(Double principal) { this.principal = principal; }
    public Double getAnnualRate() { return annualRate; }
    public void setAnnualRate(Double annualRate) { this.annualRate = annualRate; }
    public Integer getTermYears() { return termYears; }
    public void setTermYears(Integer termYears) { this.termYears = termYears; }
    public Double getMonthlyPayment() { return monthlyPayment; }
    public void setMonthlyPayment(Double monthlyPayment) { this.monthlyPayment = monthlyPayment; }
}