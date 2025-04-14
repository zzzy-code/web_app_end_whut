package server.app_end.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateArticleDto {
    private Integer id;
    private String title;
    private String content;
    private String name;
}

