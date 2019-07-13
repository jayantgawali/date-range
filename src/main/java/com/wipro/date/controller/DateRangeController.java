package com.wipro.date.controller;

import com.wipro.date.domain.DateForm;
import com.wipro.date.domain.DateRangeResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class DateRangeController {
    private static final Logger logger = LoggerFactory.getLogger(DateRangeController.class);

    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    DaysCalculatorService calculatorService;

    @Autowired
    MessageSource messages;

    @PostMapping("/api/calculate")
    public ResponseEntity<DateRangeResponseBody> calculate(
            @Valid @RequestBody DateForm form, Errors errors, Locale locale) {
        logger.info("Start Date range calculation");
        DateRangeResponseBody result = new DateRangeResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {
            logger.error("There are validation errors in the request");
            result.setMessage(errors.getAllErrors()
                    .stream().map(x -> messages.getMessage(x.getDefaultMessage(), null, locale))
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(result);

        }
        try {
            long days = calculatorService.calculateDays(form.getStartDate(), form.getEndDate());
            logger.info("Number of days calculated are: " + days);
            result.setMessage(String.format(messages.getMessage("success.message",
                    new Object[]{days, FORMATTER.format(form.getStartDate()), FORMATTER.format(form.getEndDate())}, locale)));

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Error occured ", e);
            result.setMessage(messages.getMessage("unexpected.error.message", null, locale));

            return ResponseEntity.badRequest().body(result);
        }
    }

}
