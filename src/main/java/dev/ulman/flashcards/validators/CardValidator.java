package dev.ulman.flashcards.validators;

import dev.ulman.flashcards.model.Card;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CardValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Card.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "word", "Word can't be empty");
        ValidationUtils.rejectIfEmpty(errors, "translation", "Translation can't be empty");

    }
}
