package server.app_end.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import server.app_end.pojo.Article;
import server.app_end.pojo.Response;
import server.app_end.pojo.User;
import server.app_end.pojo.dto.AuthorDto;
import server.app_end.pojo.dto.LoginDto;
import server.app_end.pojo.dto.RegisterDto;
import server.app_end.pojo.dto.UpdatePwdDto;
import server.app_end.repository.ArticleRepository;
import server.app_end.service.IUserService;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final IUserService userService;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Response<User> login(@RequestBody LoginDto loginDto) {
        User user = userService.login(loginDto);
        return Response.success(user);
    }

    @PostMapping("/register")
    public Response<Void> register(
            @RequestParam String name,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam LocalDate birthday,
            @RequestParam MultipartFile file
    ) {
        try {
            // 校验是否邮箱重复
            if (userService.emailExists(email)) {
                return Response.error("邮箱已注册");
            }

            // 使用绝对路径存储文件
            String uploadDir = System.getProperty("user.dir") + File.separator + "uploads";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            // 构建文件保存路径
            String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
            File dest = new File(dir, fileName);
            file.transferTo(dest);

            // 最终存储到数据库的路径
            String avatarUrl = "http://127.0.0.1:8080/uploads/" + fileName;

            // 创建 DTO 并调用注册服务
            RegisterDto dto = new RegisterDto(name, password, email, birthday, avatarUrl);
            userService.register(dto);

            return Response.success(null);
        } catch (Exception e) {
            e.printStackTrace(); // 推荐打印日志排查问题
            return Response.error("注册失败：" + e.getMessage());
        }
    }

    @PostMapping("/updatePwd")
    public Response<Void> updatePwd(@RequestBody UpdatePwdDto UpdatePwdDto) {
        userService.updatePwd(UpdatePwdDto);
        return Response.success(null);
    }

    @GetMapping("/list")
    public Map<String, Object> getUserStats(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int pageSize) {

        List<AuthorDto> list = userService.getUserStats(page, pageSize);
        int total = userService.countUsers();

        Map<String, Object> response = new HashMap<>();
        response.put("list", list);
        response.put("total", total);
        return response;
    }

    @GetMapping("/getByName")
    public Response<User> getByName(@RequestParam String name) {
        User user = userService.getByName(name);
        return Response.success(user);
    }

}