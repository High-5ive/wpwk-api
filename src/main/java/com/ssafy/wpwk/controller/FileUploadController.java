package com.ssafy.wpwk.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.List;

@RestController
@CrossOrigin("*")
public class FileUploadController {

    @PostMapping("upload/files")
    public String uploadMulti(@RequestParam("files")List<MultipartFile> files) throws Exception{

        String basePath = FileSystemView.getFileSystemView().getHomeDirectory().toString() + "/multi";

        for(MultipartFile file : files) {
            String originalName = file.getOriginalFilename();
            String filePath = basePath + "/" + originalName;

            System.out.println(filePath);

            File dest = new File(filePath);
            file.transferTo(dest);
        }
        return "uploaded";
    }
}