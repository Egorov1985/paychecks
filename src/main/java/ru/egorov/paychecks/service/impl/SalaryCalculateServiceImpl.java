package ru.egorov.paychecks.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.egorov.paychecks.dto.RequestPayDto;
import ru.egorov.paychecks.dto.ResponsePayDto;
import ru.egorov.paychecks.service.SalaryCalculateService;
import ru.egorov.paychecks.utils.ProductionCalendar;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SalaryCalculateServiceImpl implements SalaryCalculateService {
    private final Logger logger = LoggerFactory.getLogger(SalaryCalculateServiceImpl.class);

    public ResponsePayDto calcVacationPay(RequestPayDto request) {
        LocalDate beginVacation = request.getBeginVacation();
        LocalDate endVacation = request.getEndVacation();
        double averageSalaryPerYear = request.getAverageSalary();
        long lengthVacation = ChronoUnit.DAYS.between(beginVacation, endVacation) + 1;
        Set<LocalDate> holidayList = ProductionCalendar.getHolidayList();
        for (LocalDate date : holidayList) {
            if (date.isAfter(beginVacation.minusDays(1)) && date.isBefore(endVacation.plusDays(1))) {
                lengthVacation--;
            }
        }
        double sumVacationMoney = Math.round((averageSalaryPerYear / 29.3) * lengthVacation * 100.00) / 100.00;
        logger.info("Успешно произведен расчет отпускных!");
        return new ResponsePayDto(beginVacation, endVacation, sumVacationMoney);
    }

}
