package dev.ulman.flashcards.validators;

import dev.ulman.flashcards.model.Card;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CardValidator implements Validator {

    final int MAX_EXTENSION_LENGTH = 5;
    final String[] POSSIBLE_EXTENSIONS = {".apng", ".bmp", ".gif", ".ico", ".cur", ".jpg", ".jpeg", ".jfif", ".pjpeg", ".pjp",
    ".png", ".svg", ".tif", ".tiff", ".webp"};

    @Override
    public boolean supports(Class<?> aClass) {
        return Card.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "word", "form.error.word");
        ValidationUtils.rejectIfEmpty(errors, "translation", "form.error.translation");
        ValidationUtils.rejectIfEmpty(errors, "group", "form.error.group");

        Card card = (Card) o;
        String url = card.getImageURL();
        if(url != null && url.length() > 0){
            UrlValidator urlValidator = new UrlValidator();
            if (!urlValidator.isValid(url)){
                errors.rejectValue("imageURL", "form.error.url");
            } else {
                if (!isImage(getExtension(url))){
                    errors.rejectValue("imageURL", "form.error.format");
                }
            }
        }
    }

    private String getExtension(String url){
        String extension = url.substring(url.lastIndexOf('.'));
        if (MAX_EXTENSION_LENGTH > extension.length()){
            return extension;
        }
        return "";
    }

    private boolean isImage(String extension) {
        if(extension.length() < 3) return false;
        for (String ext :  POSSIBLE_EXTENSIONS){
            if(extension.equals(ext)) return true;
        }
        return false;
    }
}
