package server.app_end.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.app_end.pojo.Author;
import server.app_end.pojo.Response;
import server.app_end.pojo.dto.AddDto;
import server.app_end.pojo.dto.DeleteDto;
import server.app_end.pojo.dto.EditDto;
import server.app_end.service.IAuthorService;

import java.util.HashMap;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/api/author")
public class AuthorController {
    @Autowired
    private IAuthorService authorService;

    @PostMapping("/add")
    public Response<Author> add(@RequestBody AddDto addDto) {
        Author author=authorService.add(addDto);
        return Response.success(author);
    }

    @PostMapping("/edit")
    public Response<Author> edit(@RequestBody EditDto editDto) {
        Author author = authorService.edit(editDto);
        return Response.success(author);
    }
    
    @PostMapping("/delete")
    public Response<Void> delete(@RequestBody DeleteDto deleteDto) {
        authorService.delete(deleteDto);
        return Response.success(null);
    }

    @GetMapping("/list")
    public Response<Object> list(@RequestParam Integer page, @RequestParam Integer pageSize,
            @RequestParam(required = false) String keyword) {
        Map<String, Object> response = new HashMap<>();
        response.put("authors", authorService.list(page, pageSize, keyword));
        response.put("total", authorService.count(keyword));
        return Response.success(response);
    }
    
}