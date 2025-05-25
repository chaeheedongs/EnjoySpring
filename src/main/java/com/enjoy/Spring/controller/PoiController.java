package com.enjoy.Spring.controller;

import com.enjoy.Spring.service.poi.PoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/poi")
public class PoiController {

    private final PoiService poiService;

    @Autowired
    public PoiController(final PoiService poiService) {
        this.poiService = poiService;
    }

    @GetMapping("/view")
    public String index() {
        return "poi/view";
    }

    @GetMapping("/view/upload")
    public String poiUpload() {
        return "poi/upload";
    }

    @GetMapping("/view/download")
    public String poiDownload() {
        return "poi/download";
    }

    @PostMapping("/upload-excel")
    public String uploadFile(@RequestParam MultipartFile file) {
        poiService.upload(file);
        return "redirect:/poi/view/upload";
    }

    @PostMapping("/upload-excels")
    public String uploadFiles(@RequestParam MultipartFile[] files) {
        poiService.uploadFiles(files);
        return "redirect:/poi/view/upload";
    }

    @PostMapping("/download-excel")
    public String downloadFile(HttpServletResponse response) {
        poiService.download(response);

        return "redirect:/poi/view/download";
    }
}
