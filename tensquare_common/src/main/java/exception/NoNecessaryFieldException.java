package exception;

/**
 * @Description: 缺少必要字段异常类
 * @Author 盛春强
 * @Date 2021/7/13 17:07
 */
public class NoNecessaryFieldException extends RuntimeException {

    public NoNecessaryFieldException() {
        super();
    }

    public NoNecessaryFieldException(String message) {
        super(message);
    }
}
