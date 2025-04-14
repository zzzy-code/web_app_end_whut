package server.app_end.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    private Integer id;
    private LocalDateTime date;
    private String name;
    private String province;
    private String city;
    private String address;
    private String zip;
}
