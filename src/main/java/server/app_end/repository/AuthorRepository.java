package server.app_end.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import server.app_end.pojo.Author;

import java.util.List;

@Mapper
@Repository
public interface AuthorRepository {
    void add(Author author);

    void edit(Author author);

    void delete(Author author);

    List<Author> list(Integer page, Integer pageSize, String keyword);

    Integer count(String keyword);
}