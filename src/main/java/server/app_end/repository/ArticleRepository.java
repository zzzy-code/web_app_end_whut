package server.app_end.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import server.app_end.pojo.Article;
import server.app_end.pojo.dto.AuthorDto;

import java.util.List;

@Mapper
@Repository
public interface ArticleRepository {

    // 添加文章
    void add(Article article);

    // 编辑文章
    void edit(Article article);

    // 删除文章
    void deleteById( Integer id);

    List<Article> list(
            @Param("offset") int offset,
            @Param("pageSize") int pageSize,
            @Param("keyword") String keyword,
            @Param("authorName") String authorName
    );

    int count(
            @Param("keyword") String keyword,
            @Param("authorName") String authorName
    );

    // 根据 ID 查询单个文章
    Article findById(Integer id);

    List<AuthorDto> getAuthorStats(Integer offset, Integer pageSize);

}
