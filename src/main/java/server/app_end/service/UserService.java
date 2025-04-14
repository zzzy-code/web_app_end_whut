package server.app_end.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import server.app_end.exception.InvalidCredentialsException;
import server.app_end.exception.UserAlreadyExistsException;
import server.app_end.pojo.User;
import server.app_end.pojo.dto.AuthorDto;
import server.app_end.pojo.dto.LoginDto;
import server.app_end.pojo.dto.RegisterDto;
import server.app_end.pojo.dto.UpdatePwdDto;
import server.app_end.repository.UserRepository;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User login(LoginDto loginDto) {
        User user = userRepository.getByName(loginDto.getName());
        if (user != null && passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            return user;
        } else {
            throw new InvalidCredentialsException();
        }
    }

    @Override
    public void register(RegisterDto registerDto) {
        User user = userRepository.getByName(registerDto.getName());
        if (user != null) {
            throw new UserAlreadyExistsException();
        } else {
            User newUser = new User();
            BeanUtils.copyProperties(registerDto, newUser);
            newUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            userRepository.add(newUser);
        }
    }

    @Override
    public void updatePwd(UpdatePwdDto updatePwdDto) {
        User user = userRepository.getByName(updatePwdDto.getName());
        if (user != null && passwordEncoder.matches(updatePwdDto.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(updatePwdDto.getNewPassword()));
            userRepository.update(user);
        } else {
            throw new InvalidCredentialsException();
        }
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);  // 根据数据库字段判断邮箱是否已存在
    }

    @Override
    public List<AuthorDto> getUserStats(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return userRepository.getUserArticleStats(offset, pageSize);
    }

    @Override
    public int countUsers() {
        return userRepository.countUsers();
    }

    @Override
    public User getByName(String name){
        return userRepository.getByName(name);
    }
}
