package se.lexicon;

public class InsufficientFoundsException extends Exception{
    // step 1: extends custom created Exception with parent class Exception

    //fields
    /**private String message; */
    //if we pass the message to parent Class -> super(message),then we don't need to declare here
    private Integer errorCode;

    //constructor
    public InsufficientFoundsException(String message, Integer errorCode){
        super(message); //to pass message to the parent class Exception
        //this.message=message;
        //if we pass the message to parent Class, we don't need to declare here
        this.errorCode=errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
