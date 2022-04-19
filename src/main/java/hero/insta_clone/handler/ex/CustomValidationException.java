package hero.insta_clone.handler.ex;

import java.util.Map;

public class CustomValidationException extends RuntimeException {
    private Map<String, String> errorMap;

    public CustomValidationException(String message) {
        super(message);
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }
}
