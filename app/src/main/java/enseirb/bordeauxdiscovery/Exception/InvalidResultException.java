package enseirb.bordeauxdiscovery.Exception;

public class InvalidResultException extends Exception{
    public InvalidResultException(){
        super("Invalid Result Exception");
    }

    public InvalidResultException(String message){
        super(message);
    }
}
