package entity;


import lombok.*;

/**
 * @Description: 基础返回对象
 * @Author 盛春强
 * @Date 2021/7/13 11:54
 */
@Getter
@Setter
@ToString
public class Result {
    private boolean flag;
    private Integer code;
    private String message;
    private Object data;

    public Result() {
        this(true, StatusCode.OK, "请求成功");
    }

    public Result(Object data) {
        this(true, StatusCode.OK, "请求成功");
        this.data = data;
    }

    public Result(String message) {
        this(true, StatusCode.OK, message);
    }

    public Result(String message, Object data) {
        this(true, StatusCode.OK, message, data);
    }

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result(boolean flag, Integer code, String message, Object data) {
        this(flag, code, message);
        this.data = data;
    }
}
