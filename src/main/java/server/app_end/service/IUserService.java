package server.app_end.service;

import org.springframework.stereotype.Service;
import server.app_end.pojo.User;
import server.app_end.pojo.dto.AuthorDto;
import server.app_end.pojo.dto.LoginDto;
import server.app_end.pojo.dto.RegisterDto;
import server.app_end.pojo.dto.UpdatePwdDto;

import java.util.List;

@Service
public interface IUserService {
    User login(LoginDto loginDto);

    void register(RegisterDto registerDto);

    void updatePwd(UpdatePwdDto updatePwdDto);

    boolean emailExists(String email);

    User getByName(String name);

    List<AuthorDto> getUserStats(int page, int pageSize);
    int countUsers();
}
