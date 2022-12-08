package se.lexicon;

public class DataNotFoundException extends Exception{
    //any exception (even customize exception) must be a subclass of Exception -> extends Exception

    private String paramValue;
    private String message; //error message
    private Integer errorCode;

    public DataNotFoundException(String message, String paramValue){
        super(message); //to tell the parent class that this is the error message
        this.message=message;
        this.paramValue=paramValue;

    }
    public DataNotFoundException(String message, Integer errorCode, String paramValue){
        this(message, paramValue); //reuse the first constructor
        this.errorCode=errorCode;


    }

    //getter methods if needed


    @Override // method already exists in parent class Exception
    public String getMessage() {
        return message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getParamValue() {
        return paramValue;
    }
}
