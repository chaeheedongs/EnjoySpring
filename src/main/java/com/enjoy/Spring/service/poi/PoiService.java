package com.enjoy.Spring.service.poi;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface PoiService {
    void upload(MultipartFile file);
    void uploadFiles(MultipartFile[] files);
    void download(HttpServletResponse response);
}
