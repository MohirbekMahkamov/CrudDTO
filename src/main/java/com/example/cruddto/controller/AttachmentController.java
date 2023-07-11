package com.example.cruddto.controller;

import com.example.cruddto.repository.AttachmentContentRepository;
import com.example.cruddto.repository.AttachmentRepository;
import com.example.cruddto.upload.Attachment;
import com.example.cruddto.upload.AttachmentContent;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Optional;

@RestController
public class AttachmentController {
    private static final String url = "Cloud";
    @Autowired
    AttachmentContentRepository attachmentContentRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @PostMapping("/upload")
    public String uploadFile(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file != null){
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();
            String contentType = file.getContentType();
            Attachment attachment = new Attachment();
            attachment.setSize(size);
            attachment.setOriginalName(originalFilename);
            attachment.setContentType(contentType);
            Attachment saveAttachment = attachmentRepository.save(attachment);
            AttachmentContent attachmentContent = new AttachmentContent();
            attachmentContent.setContent(file.getBytes());
            attachmentContent.setAttachment(saveAttachment);
            attachmentContentRepository.save(attachmentContent);
            return "File saqlandi id:=" + saveAttachment.getId();
        }
        return "ERROR";
    }
    @PostMapping("/uploadSource")
    public String uploadFileSource(MultipartHttpServletRequest request) throws IOException {

        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file != null){
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();
            String contentType = file.getContentType();
            Attachment attachment = new Attachment();
            attachment.setSize(size);
            attachment.setOriginalName(originalFilename);
            attachment.setContentType(contentType);
            Attachment saveAttachment = attachmentRepository.save(attachment);
            Path path = Paths.get(url+"/"+attachment.getOriginalName());
            Files.copy(file.getInputStream(),path);

            return "File saqlandi id:=" + saveAttachment.getId();
        }
        return "ERROR";
    }
    @GetMapping("/getFile/{id}")
    public void getFile(@PathVariable Integer id, HttpServletResponse httpServletResponse) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()){

            Attachment attachment = optionalAttachment.get();
            Optional<AttachmentContent> attachmentContent = attachmentContentRepository.findByAttachmentId(id);
            if (attachmentContent.isPresent()){
                AttachmentContent attachmentContent1 = attachmentContent.get();
                httpServletResponse.setHeader("Content-Disposition","attachment; filename=\""+attachment.getOriginalName()+"\"");
                httpServletResponse.setContentType(attachment.getContentType());
                FileCopyUtils.copy(attachmentContent1.getContent(),httpServletResponse.getOutputStream());

            }

        }


    }

    @GetMapping("/getFileSource/{id}")
    public void getFileSource(@PathVariable Integer id, HttpServletResponse httpServletResponse) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()){
            Attachment attachment = optionalAttachment.get();
                httpServletResponse.setHeader("Content-Disposition","attachment; filename=\""+attachment.getOriginalName()+"\"");
                httpServletResponse.setContentType(attachment.getContentType());
                FileInputStream fileInputStream = new FileInputStream(url+"/"+attachment.getOriginalName());
                FileCopyUtils.copy(fileInputStream,httpServletResponse.getOutputStream());

            }

        }

}
