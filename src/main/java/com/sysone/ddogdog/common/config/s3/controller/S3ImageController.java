package com.sysone.ddogdog.common.config.s3.controller;

import com.sysone.ddogdog.common.config.s3.service.S3ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
public class S3ImageController {

    private final S3ImageService s3ImageService;

    @PostMapping("/s3/upload")
    public ResponseEntity<?> s3Upload(@RequestPart(value = "image", required = false) MultipartFile image){
        log.info("업로드 진입");
        String profileImage = s3ImageService.upload(image);
        return ResponseEntity.ok(profileImage);
    }

    @GetMapping("/s3/delete")
    public ResponseEntity<?> s3delete(@RequestParam String addr){
        s3ImageService.deleteImageFromS3(addr);
        return ResponseEntity.ok(null);
    }
}
