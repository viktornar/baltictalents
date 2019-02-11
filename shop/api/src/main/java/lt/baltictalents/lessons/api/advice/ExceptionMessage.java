package lt.baltictalents.lessons.api.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ExceptionMessage {
    @Getter
    @Setter
    public String message;
}