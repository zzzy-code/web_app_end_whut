package server.app_end.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> Response<T> success(T data) {
        return new Response<>(200, "success", data);
    }
    public static <T> Response<T> error(String msg) {return new Response<>(500, msg, null);}
}
