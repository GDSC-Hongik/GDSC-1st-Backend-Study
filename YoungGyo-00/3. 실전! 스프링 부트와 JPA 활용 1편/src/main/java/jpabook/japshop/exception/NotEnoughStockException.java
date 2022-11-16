package jpabook.japshop.exception;

import org.aspectj.weaver.ast.Not;

public class NotEnoughStockException extends RuntimeException{

    public NotEnoughStockException() {}

    public NotEnoughStockException(String message) {
        super(message);
    }

    public NotEnoughStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughStockException(Throwable cause) {
        super(cause);
    }
}
