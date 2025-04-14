package server.app_end.service;

import org.springframework.stereotype.Service;
import server.app_end.pojo.Author;
import server.app_end.pojo.dto.AddDto;
import server.app_end.pojo.dto.DeleteDto;
import server.app_end.pojo.dto.EditDto;

import java.util.List;

@Service
public interface IAuthorService {
    Author add(AddDto addDto);

    Author edit(EditDto editDto);

    void delete(DeleteDto deleteDto);

    List<Author> list(Integer page, Integer pageSize, String keyword);

    Integer count(String keyword);

}