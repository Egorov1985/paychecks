package ru.egorov.paychecks.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.egorov.paychecks.dto.RequestPayDto;
import ru.egorov.paychecks.dto.ResponsePayDto;
import ru.egorov.paychecks.service.impl.SalaryCalculateServiceImpl;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class
SalaryTransactionsController {
    private final SalaryCalculateServiceImpl salaryCalculateService;

    @GetMapping("/calculate")
    public ResponseEntity<ResponsePayDto> calculate(@Valid RequestPayDto requestPayDto) {
        ResponsePayDto response = salaryCalculateService.calcVacationPay(requestPayDto);
        return ResponseEntity.ok(response);
    }
}
