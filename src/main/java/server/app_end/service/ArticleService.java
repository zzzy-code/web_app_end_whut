package server.app_end.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.app_end.exception.ArticleNotFoundException;
import server.app_end.pojo.Article;
import server.app_end.pojo.User;
import server.app_end.pojo.dto.AddArticleDto;
import server.app_end.pojo.dto.AuthorDto;
import server.app_end.pojo.dto.UpdateArticleDto;
import server.app_end.repository.ArticleRepository;
import server.app_end.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleService implements IArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Article add(AddArticleDto dto) {
        Article article = new Article();
        BeanUtils.copyProperties(dto, article);
        articleRepository.add(article);

        return article;
    }

    @Override
    public Article edit(UpdateArticleDto dto) {
        Article article = new Article();
        BeanUtils.copyProperties(dto, article);
        articleRepository.edit(article);
        return articleRepository.findById(article.getId());
    }

    @Override
    public void deleteById(Integer id) {
        // 先检查文章是否存在
        Article article = articleRepository.findById(id);
        if (article == null) {
            // 可以抛出异常，通知调用方文章不存在
            throw new ArticleNotFoundException("文章不存在，ID: " + id);
        }
        // 删除文章
        articleRepository.deleteById(id);

    }

    @Override
    public Map<String, Object> list(Integer page, Integer pageSize, String keyword, String authorName) {
        int offset = (page - 1) * pageSize;
        List<Article> articles = articleRepository.list(offset, pageSize, keyword, authorName);
        int total = articleRepository.count(keyword, authorName);

        Map<String, Object> result = new HashMap<>();
        result.put("article", articles);
        result.put("total", total);
        return result;
    }


    @Override
    public Integer count(String keyword,String authorName) {
        return articleRepository.count(keyword,authorName);
    }

    @Override
    public List<AuthorDto> getAuthorStats(Integer page, Integer pageSize) {
        int offset = (page - 1) * pageSize;
        return articleRepository.getAuthorStats(offset, pageSize);
    }

}
