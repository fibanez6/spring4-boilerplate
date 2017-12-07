package com.fibanez.spring4basic.web.validator;

import com.fibanez.spring4basic.model.Customer;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by fernandoi on 07/12/17.
 */
@Component("beforeCreateWebsiteUserValidator")
public class CustomerValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Customer.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors e) {

        ValidationUtils.rejectIfEmpty(e, "firstName", "name.empty");

        Customer p = (Customer) target;

        //perform additional checks
        //if name already exists or ?
    }
}
