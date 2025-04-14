package server.app_end.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.app_end.pojo.Article;
import server.app_end.pojo.Response;
import server.app_end.pojo.dto.AddArticleDto;
import server.app_end.pojo.dto.DeleteArticleDto;
import server.app_end.pojo.dto.UpdateArticleDto;
import server.app_end.service.IArticleService;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    @PostMapping("/add")
    public Response<Article> addArticle(@RequestBody AddArticleDto addArticleDto) {
        Article article = articleService.add(addArticleDto);
        return Response.success(article);
    }

    @PostMapping("/edit")
    public Response<Article> editArticle(@RequestBody UpdateArticleDto updateArticleDto) {
        Article article = articleService.edit(updateArticleDto);
        return Response.success(article);
    }

    // 删除文章
    @PostMapping("/delete")
    public Response<Void> deleteArticle(@RequestBody DeleteArticleDto deleteDto) {
        try {
            articleService.deleteById(deleteDto.getId());
            return Response.success(null);
        } catch (Exception e) {
            return Response.error("删除失败");
        }
    }

    // 获取文章列表（分页 + 关键词）
    @GetMapping("/list")
    public Response<Map<String, Object>> listArticles(
            @RequestParam Integer page,
            @RequestParam Integer pageSize,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String authorName) {

        Map<String, Object> result = articleService.list(page, pageSize, keyword, authorName);
        return Response.success(result);
    }

}
