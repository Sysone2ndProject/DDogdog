package com.sysone.ddogdog.common.config.s3.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.sysone.ddogdog.common.config.s3.Exception.ErrorCode;
import com.sysone.ddogdog.common.config.s3.Exception.S3Exception;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class S3ImageService {

    private final AmazonS3Client amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    /**
     * [ S3에 저장된 객체의 url을 반환하는 메서드 ]
     *
     * @param image
     * @return String image url
     */
    public String upload(MultipartFile image) {
        log.info("upload진입");
        if (image.isEmpty() || Objects.isNull(image.getOriginalFilename())) {
            throw new S3Exception(ErrorCode.EMPTY_FILE_EXCEPTION);
        }
        return this.uploadImage(image);
    }

    /**
     * [ 호출함수 - 1. 확장자명 검증 메서드 호출 2. 이미지 업로드 메서드로 반환 ]
     *
     * @param image
     * @return uploadImageToS3 호출
     * @throws S3Exception 이미지 업로드 실패
     */
    private String uploadImage(MultipartFile image) {
        log.info("사전 검사");
        this.validateImageFileExtention(image.getOriginalFilename());
        try {
            return this.uploadImageToS3(image);
        } catch (IOException e) {
            throw new S3Exception(ErrorCode.IO_EXCEPTION_ON_IMAGE_UPLOAD);
        }
    }

    /**
     * [ 파일 확장자 검증 메서드 ]
     *
     * @param filename - image.getOriginalFilename()
     * @throws S3Exception 일치하는 확장자가 없습니다
     */
    private void validateImageFileExtention(String filename) {
        log.info("확장자 검사");
        int lastDotIndex = filename.lastIndexOf(".");
        if (lastDotIndex == -1) {
            throw new S3Exception(ErrorCode.NO_FILE_EXTENTION);
        }

        String extention = filename.substring(lastDotIndex + 1).toLowerCase();
        List<String> allowedExtentionList = Arrays.asList("jpg", "jpeg", "png", "gif");

        if (!allowedExtentionList.contains(extention)) {
            throw new S3Exception(ErrorCode.INVALID_FILE_EXTENTION);
        }
    }

    /**
     * [ S3에 이미지 업로드하는 메서드 ]
     *
     * @param image - 업로드하는 이미지
     * @return url - 업로드완료된 버켓과 이미지 url 반환
     * @throws S3Exception S3에 업로드하던 도중 오류가 발생했습니다
     */
    private String uploadImageToS3(MultipartFile image) throws IOException {
        log.info("upload이미지진입");
        String originalFilename = image.getOriginalFilename();
        String extention = originalFilename.substring(originalFilename.lastIndexOf("."));
        log.info("파일 확장자: {}", extention);
        String s3FileName = UUID.randomUUID().toString().substring(0, 10) + originalFilename;

        InputStream is = image.getInputStream();
        byte[] bytes = IOUtils.toByteArray(is);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/" + extention);
        metadata.setContentLength(bytes.length);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        try {
            PutObjectRequest putObjectRequest =
                    new PutObjectRequest(bucketName, s3FileName, byteArrayInputStream, metadata);
            log.info(putObjectRequest.toString());
            amazonS3.putObject(putObjectRequest);
            log.info("파일 업로드 성공: {}", s3FileName);
        } catch (Exception e) {
            log.error("S3 업로드 실패", e);
            throw new S3Exception(ErrorCode.PUT_OBJECT_EXCEPTION);
        } finally {
            byteArrayInputStream.close();
            is.close();
        }

        return amazonS3.getUrl(bucketName, s3FileName).toString();
    }

    /**
     * [ 이미지 제거 메서드 ]
     *
     * @param imageAddress - 이미지 url 받아 getKeyFromImageAddress()를 호출하여 삭제에 필요한 key 얻음
     * @throws S3Exception 이미지 삭제하던 중 오류발생
     */
    public void deleteImageFromS3(String imageAddress) {
        String key = getKeyFromImageAddress(imageAddress);
        try {
            amazonS3.deleteObject(new DeleteObjectRequest(bucketName, key));
        } catch (Exception e) {
            throw new S3Exception(ErrorCode.IO_EXCEPTION_ON_IMAGE_DELETE);
        }
    }

    /**
     * [ url에서 key값 받아오는 메서드 ]
     *
     * @param imageAddress - 이미지 url 받아 key 분리
     * @return decodingKey
     * @throws S3Exception 이미지 삭제하던 중 오류발생
     */
    private String getKeyFromImageAddress(String imageAddress) {
        try {
            URL url = new URL(imageAddress);
            String decodingKey = URLDecoder.decode(url.getPath(), "UTF-8");
            return decodingKey.substring(1); // 맨 앞의 '/' 제거
        } catch (MalformedURLException | UnsupportedEncodingException e) {
            throw new S3Exception(ErrorCode.IO_EXCEPTION_ON_IMAGE_DELETE);
        }
    }
}