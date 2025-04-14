package server.app_end.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.app_end.pojo.Author;
import server.app_end.pojo.dto.AddDto;
import server.app_end.pojo.dto.DeleteDto;
import server.app_end.pojo.dto.EditDto;
import server.app_end.repository.AuthorRepository;

import java.util.List;

@Service
public class AuthorService implements IAuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author add(AddDto addDto) {
        Author author = new Author();
        BeanUtils.copyProperties(addDto, author);
        authorRepository.add(author);
        return author;
    }

    @Override
    public Author edit(EditDto editDto) {
        Author author = new Author();
        BeanUtils.copyProperties(editDto, author);
        authorRepository.edit(author);
        return author;
    }

    @Override
    public void delete(DeleteDto deleteDto) {
        Author author = new Author();
        BeanUtils.copyProperties(deleteDto, author);
        authorRepository.delete(author);
    }

    @Override
    public List<Author> list(Integer page, Integer pageSize, String keyword) {
        return authorRepository.list(page, pageSize, keyword);
    }

    @Override
    public Integer count(String keyword) {
        return authorRepository.count(keyword);
    }
}
