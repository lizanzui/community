package com.lizan.community.controller;


import com.lizan.community.dto.FileDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;

/**
 * Created by codedrinker on 2019/6/26.
 */
@Controller
//@Slf4j
public class FileController {
//    @Autowired
//    private UCloudProvider uCloudProvider;

    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(HttpServletRequest request) {
//        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//        MultipartFile file = multipartRequest.getFile("editormd-image-file");
//        try {
//            String fileName = uCloudProvider.upload(file.getInputStream(), file.getContentType(), file.getOriginalFilename());
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(1);
            fileDTO.setUrl("/images/logo.png");
            return fileDTO;
//        } catch (Exception e) {
//            log.error("upload error", e);
//            FileDTO fileDTO = new FileDTO();
//            fileDTO.setSuccess(0);
//            fileDTO.setMessage("上传失败");
//            return fileDTO;
//        }
    }
}
