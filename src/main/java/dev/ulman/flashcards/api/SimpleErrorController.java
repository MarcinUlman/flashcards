package dev.ulman.flashcards.api;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class SimpleErrorController implements ErrorController {

    public SimpleErrorController() {
    }

    @GetMapping("/error")
    public String errorHandling(HttpServletRequest request){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null){
            Integer statusCode = Integer.valueOf(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value())
                return "errors/error-404";
            else if (statusCode == HttpStatus.FORBIDDEN.value())
                return "errors/error-403";
            else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value())
                return "errors/error-500";
            else if (statusCode == HttpStatus.INSUFFICIENT_STORAGE.value())
                return "errors/error-507";
        }
        return "errors/error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
