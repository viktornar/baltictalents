package lt.baltictalents.lessons.api.advice;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lt.baltictalents.lessons.api.advice.ExceptionMessage;

import lt.baltictalents.lessons.api.exception.ProductNotFoundException;

@ControllerAdvice
class ProductNotFoundAdvice {
	@ResponseBody
	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	ExceptionMessage productNotFoundHandler(ProductNotFoundException ex) {
		return new ExceptionMessage(ex.getMessage());
	}
}