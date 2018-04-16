package application.Exceptions;


public class LanguageServerNotFoundException extends Exception{
    @Override
    public String getMessage() {
        return "Language server not found. Try to use another language";
    }
}
