package exceptions;

public class ValorIgualAntigoException extends RuntimeException{
    public ValorIgualAntigoException (String msg) {
        super(msg);
    }

    public ValorIgualAntigoException (Throwable cause) {
        super(cause);
    }

}
