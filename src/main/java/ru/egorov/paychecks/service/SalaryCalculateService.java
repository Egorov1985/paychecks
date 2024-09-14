package ru.egorov.paychecks.service;

import ru.egorov.paychecks.dto.RequestPayDto;
import ru.egorov.paychecks.dto.ResponsePayDto;

public interface SalaryCalculateService {

    ResponsePayDto calcVacationPay(RequestPayDto request);
}
