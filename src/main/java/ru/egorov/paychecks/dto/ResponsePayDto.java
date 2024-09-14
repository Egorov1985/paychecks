package ru.egorov.paychecks.dto;

import java.time.LocalDate;


public class ResponsePayDto {
    private LocalDate beginVacation;
    private LocalDate endVacation;
    private double vacationMoney;

    public ResponsePayDto() {
    }

    public ResponsePayDto(LocalDate beginVacation, LocalDate endVacation, double vacationMoney) {
        this.beginVacation = beginVacation;
        this.endVacation = endVacation;
        this.vacationMoney = vacationMoney;
    }

    public LocalDate getBeginVacation() {
        return beginVacation;
    }

    public void setBeginVacation(LocalDate beginVacation) {
        this.beginVacation = beginVacation;
    }

    public LocalDate getEndVacation() {
        return endVacation;
    }

    public void setEndVacation(LocalDate endVacation) {
        this.endVacation = endVacation;
    }

    public double getVacationMoney() {
        return vacationMoney;
    }

    public void setVacationMoney(double vacationMoney) {
        this.vacationMoney = vacationMoney;
    }


}
