package ru.konry.stud_fin_assistent.exceptions;

public class CityRegisterException extends Exception
{
    private String code;
    public CityRegisterException() {
    }

    public CityRegisterException(String message, String code) {
        super(message);
        this.code = code;
    }

    public CityRegisterException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
