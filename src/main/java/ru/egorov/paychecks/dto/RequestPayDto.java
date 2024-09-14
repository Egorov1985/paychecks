package ru.egorov.paychecks.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RequestPayDto {
    @NotNull(message = "{message.beginVacation}")
    private LocalDate beginVacation;
    @NotNull(message = "{message.endVacation}")
    private LocalDate endVacation;
    @Min(message = "{message.averageSalary}", value = 1)
    private double averageSalary;
    public RequestPayDto() {
    }

    public RequestPayDto(LocalDate beginVacation, LocalDate endVacation, double averageSalary) {
        this.beginVacation = beginVacation;
        this.endVacation = endVacation;
        this.averageSalary = averageSalary;
    }

    public LocalDate getBeginVacation() {
        return beginVacation;
    }

    public void setBeginVacation(String beginVacation) {
        this.beginVacation = LocalDate.parse(beginVacation, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public LocalDate getEndVacation() {
        return endVacation;
    }

    public void setEndVacation(String endVacation) {
        this.endVacation = LocalDate.parse(endVacation, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public double getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(double averageSalary) {
        this.averageSalary = averageSalary;
    }
}

