package server.app_end.pojo.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteArticleDto {
    private Integer id;
    private String title;
    private String content;
    private String name;
}
