package server.app_end.service;

import org.springframework.stereotype.Service;
import server.app_end.pojo.Article;
import server.app_end.pojo.dto.AddArticleDto;
import server.app_end.pojo.dto.AuthorDto;
import server.app_end.pojo.dto.UpdateArticleDto;

import java.util.List;
import java.util.Map;

@Service
public interface IArticleService {

    // 添加文章，并自动更新用户的文章数
    Article add(AddArticleDto dto);

    // 修改文章
    Article edit(UpdateArticleDto dto);

    // 删除文章
    void deleteById(Integer id);

    // 获取文章列表（分页 + 搜索）
    Map<String, Object> list(Integer page, Integer pageSize, String keyword, String authorName);

    // 获取文章总数（分页用）
    Integer count(String keyword,String authorName);

    List<AuthorDto> getAuthorStats(Integer page, Integer pageSize);
}
