package ru.kolivim.estimates.calculator.impl.service.form;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kolivim.estimates.calculator.impl.swing.form.RegistrationForm;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class FormService {

    private final  RegistrationForm registrationForm;

    public void startRegistrationForm() {
        log.info("FormService:startRegistrationForm() startMethod");
        registrationForm.create();
        log.info("FormService:startRegistrationForm() endMethod");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        System.out.println("Yaaah, I am running........");
    }


}
