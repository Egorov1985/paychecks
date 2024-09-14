package ru.egorov.paychecks.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.egorov.paychecks.dto.RequestPayDto;
import ru.egorov.paychecks.dto.ResponsePayDto;
import ru.egorov.paychecks.service.impl.SalaryCalculateServiceImpl;
import ru.egorov.paychecks.utils.ProductionCalendar;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SalaryCalculateServiceTest {

    @InjectMocks
    private SalaryCalculateServiceImpl salaryCalculateService;

    private RequestPayDto request;
    private LocalDate beginVacation;
    private LocalDate endVacation;
    private double averageSalary;

    @BeforeEach
    void setup() {
        beginVacation = LocalDate.of(2024, 8, 5);
        endVacation = LocalDate.of(2024, 8, 18);
        averageSalary = 100000;
        request = new RequestPayDto(beginVacation, endVacation, averageSalary);
        mockStatic(ProductionCalendar.class);
    }

    @Test
    public void whenNotHolidayBetweenBeginAndEndVacation() {
        when(ProductionCalendar.getHolidayList()).thenReturn(Set.of());
        ResponsePayDto response = salaryCalculateService.calcVacationPay(request);
        assertEquals(request.getBeginVacation(), response.getBeginVacation());
        assertEquals(request.getEndVacation(), response.getEndVacation());
        assertEquals(47781.57, response.getVacationMoney());
    }

    @Test
    public void whenContainsOneHolidayBetweenBeginAndEndVacation() {
        when(ProductionCalendar.getHolidayList()).thenReturn(Set.of(LocalDate.of(2024, 8, 9)));
        ResponsePayDto response = salaryCalculateService.calcVacationPay(request);
        assertEquals(request.getBeginVacation(), response.getBeginVacation());
        assertEquals(request.getEndVacation(), response.getEndVacation());
        assertEquals(44368.60, response.getVacationMoney());
    }

    @Test
    public void whenBeginAndEndVacationEquals() {
        RequestPayDto newRequest = new RequestPayDto(beginVacation, beginVacation, averageSalary);
        when(ProductionCalendar.getHolidayList()).thenReturn(Set.of());
        ResponsePayDto response = salaryCalculateService.calcVacationPay(newRequest);
        long lengthVacation = ChronoUnit.DAYS.between(response.getBeginVacation(), response.getEndVacation()) + 1;
        assertEquals(1, lengthVacation);
        assertEquals(3412.97, response.getVacationMoney(), 0.01);
    }

}