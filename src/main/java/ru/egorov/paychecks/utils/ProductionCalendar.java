package ru.egorov.paychecks.utils;

import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

@UtilityClass
public class ProductionCalendar {
    private final Logger logger = LoggerFactory.getLogger(ProductionCalendar.class);

    @Value("${app.holiday.new-year.length}")
    private int lengthNewYearHoliday;
    private  Set<LocalDate> holidayList = new HashSet<>();


    @PostConstruct
    private void initHolidayList() {
        IntStream.range(1, lengthNewYearHoliday + 1).forEach(day -> holidayList.add(LocalDate.of(LocalDate.now().getYear(), Month.JANUARY, day)));
        holidayList.add(LocalDate.of(LocalDate.now().getYear(), Month.FEBRUARY, 23));
        holidayList.add(LocalDate.of(LocalDate.now().getYear(), Month.MARCH, 8));
        holidayList.add(LocalDate.of(LocalDate.now().getYear(), Month.MAY, 1));
        holidayList.add(LocalDate.of(LocalDate.now().getYear(), Month.MAY, 9));
        holidayList.add(LocalDate.of(LocalDate.now().getYear(), Month.JUNE, 12));
        holidayList.add(LocalDate.of(LocalDate.now().getYear(), Month.NOVEMBER, 4));
    }

    public Set<LocalDate> getHolidayList() {
        return holidayList;
    }

    public void addHoliday(LocalDate holiday) {
        if (holidayList.add(holiday)) {
            logger.info("В список праздничных дней успешно добавлен " + holiday);
        } else {
            logger.info("Ошибка добавления.");
        }
    }

    public void removeHoliday(LocalDate holiday) {
        holidayList.remove(holiday);
        logger.info("Из списка праздничных дней удален " + holiday);
    }
}
