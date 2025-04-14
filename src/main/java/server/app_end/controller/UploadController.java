package server.app_end.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import server.app_end.pojo.Response;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UploadController {

    @PostMapping("/upload")
    public Response<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Response.error("文件为空");
        }

        // 推荐：使用绝对路径写法
        String uploadDir = System.getProperty("user.dir") + File.separator + "uploads";
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        // 获取原始文件名和后缀
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        // 生成唯一文件名
        String filename = UUID.randomUUID() + suffix;

        // 最终保存路径
        File dest = new File(dir, filename);
        try {
            file.transferTo(dest);
            // 返回前端可访问的 URL 路径
            return Response.success("/uploads/" + filename);
        } catch (IOException e) {
            e.printStackTrace();
            return Response.error("上传失败：" + e.getMessage());
        }
    }
}
