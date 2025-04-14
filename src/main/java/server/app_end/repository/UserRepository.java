package server.app_end.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import server.app_end.pojo.User;
import server.app_end.pojo.dto.AuthorDto;

import java.util.List;

@Mapper
@Repository
public interface UserRepository {
    void add(User user);

    void update(User user);

    User getByName(String name);

    // 检查邮箱是否已存在
    boolean existsByEmail(String email);

    List<AuthorDto> getUserArticleStats(Integer offset,Integer pageSize);

    int countUsers(); // 总用户数
}

