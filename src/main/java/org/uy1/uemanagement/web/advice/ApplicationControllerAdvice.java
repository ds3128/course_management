package org.uy1.uemanagement.web.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.uy1.uemanagement.entities.ErrorEntity;
import org.uy1.uemanagement.execptions.*;

@ControllerAdvice
public class ApplicationControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CourseNotFoundException.class)
    public @ResponseBody ErrorEntity handlerCourseNotFoundException(CourseNotFoundException exception){
        return new ErrorEntity(null, exception.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateAuteurException.class)
    public @ResponseBody ErrorEntity handlerDuplicateAuteurException(DuplicateAuteurException exception){
        return new ErrorEntity(null, exception.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateCourseException.class)
    public @ResponseBody ErrorEntity handlerDuplicateCourseException(DuplicateCourseException exception){
        return new ErrorEntity(null, exception.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicatePhoneNumberException.class)
    public @ResponseBody ErrorEntity handlerDuplicatePhoneNumberException(DuplicatePhoneNumberException exception){
        return new ErrorEntity(null, exception.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateSupportsException.class)
    public @ResponseBody ErrorEntity handlerDuplicateSupportsException(DuplicateSupportsException exception){
        return new ErrorEntity(null, exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public @ResponseBody ErrorEntity handlerException(Exception exception){
        return new ErrorEntity(null, exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundAuteurException.class)
    public @ResponseBody ErrorEntity handlerNotFoundAuteurException(NotFoundAuteurException exception){
        return new ErrorEntity(null, exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SupportNotFoundException.class)
    public @ResponseBody ErrorEntity handlerSupportNotFoundException(SupportNotFoundException exception){
        return new ErrorEntity(null, exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public @ResponseBody ErrorEntity handlerIllegalArgumentException(IllegalArgumentException exception){
        return new ErrorEntity(null, exception.getMessage());
    }
}
