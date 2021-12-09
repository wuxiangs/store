package com.aisiono.store.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author wuxiang
 * @date 2021/12/9 11:02 上午
 */
public class OssUtil {


//    @Resource
//    private ConstantProperties constantProperties;
//
//    /**存放在阿里oss*/
//    String endpoint = constantProperties.getEndpoint();
//    String accessKeyId = constantProperties.getKeyId();
//    String accessKeySecret = constantProperties.getKeySecret();
//    String bucketName = constantProperties.getBucketName();
//    String fileHost = constantProperties.getFileHost();
//
//    /**
//     * 上传图片
//     *
//     * @param file
//     * @return
//     */
//    public String uploadImg2Oss(MultipartFile file) {
//        if (file.getSize() > 1024 * 1024 * 20) {
//            return "图片太大";
//        }
//        String originalFilename = file.getOriginalFilename();
//        String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
//        Random random = new Random();
//        String name = random.nextInt(10000) + System.currentTimeMillis() + substring;
//        try {
//            InputStream inputStream = file.getInputStream();
//            this.uploadFile2OSS(inputStream, name);
//            return name;
//        } catch (Exception e) {
//            return "上传失败";
//        }
//    }
//
//    /**
//     * 上传图片获取fileUrl
//     *
//     * @param inputStream
//     * @param fileName
//     * @return
//     */
//    private String uploadFile2OSS(InputStream inputStream, String fileName) {
//        String ret = "";
//        try {
//            //创建上传Object的Metadata
//            ObjectMetadata objectMetadata = new ObjectMetadata();
//            objectMetadata.setContentLength(inputStream.available());
//            objectMetadata.setCacheControl("no-cache");
//            objectMetadata.setHeader("Pragma", "no-cache");
//            objectMetadata.setContentType(getContentType(fileName.substring(fileName.lastIndexOf("."))));
//            objectMetadata.setContentDisposition("inline;filename=" + fileName);
//            //上传文件
//
//            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//            PutObjectResult putResult = ossClient.putObject(bucketName, fileHost +"/"+ fileName, inputStream, objectMetadata);
//            ret = putResult.getETag();
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        } finally {
//            try {
//                if (inputStream != null) {
//                    inputStream.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return ret;
//    }
//
//    public static String getContentType(String FilenameExtension) {
//        if (FilenameExtension.equalsIgnoreCase(".bmp")) {
//            return "image/bmp";
//        }
//        if (FilenameExtension.equalsIgnoreCase(".gif")) {
//            return "image/gif";
//        }
//        if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
//                FilenameExtension.equalsIgnoreCase(".jpg") ||
//                FilenameExtension.equalsIgnoreCase(".png")) {
//            return "image/jpeg";
//        }
//        if (FilenameExtension.equalsIgnoreCase(".html")) {
//            return "text/html";
//        }
//        if (FilenameExtension.equalsIgnoreCase(".txt")) {
//            return "text/plain";
//        }
//        if (FilenameExtension.equalsIgnoreCase(".vsd")) {
//            return "application/vnd.visio";
//        }
//        if (FilenameExtension.equalsIgnoreCase(".pptx") ||
//                FilenameExtension.equalsIgnoreCase(".ppt")) {
//            return "application/vnd.ms-powerpoint";
//        }
//        if (FilenameExtension.equalsIgnoreCase(".docx") ||
//                FilenameExtension.equalsIgnoreCase(".doc")) {
//            return "application/msword";
//        }
//        if (FilenameExtension.equalsIgnoreCase(".xml")) {
//            return "text/xml";
//        }
//        return "image/jpeg";
//    }
//
//    /**
//     * 获取图片路径
//     *
//     * @param fileUrl
//     * @return
//     */
//    public String getImgUrl(String fileUrl) {
//        if (StringUtils.hasLength(fileUrl)) {
//            String[] split = fileUrl.split("/");
//            String url = this.getUrl(this.fileHost +"/"+ split[split.length - 1]);
//            return url;
//        }
//        return null;
//    }
//
//    /**
//     * 获得url链接
//     *
//     * @param key
//     * @return
//     */
//    public String getUrl(String key) {
//        // 设置URL过期时间为10年  3600l* 1000*24*365*10
//        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
//        // 生成URL
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
//        if (url != null) {
//            return url.toString();
//        }
//
//        return null;
//    }
//
//
//    /**
//     * 多图片上传
//     *
//     * @param fileList
//     * @return
//     */
//    public String checkList(List<MultipartFile> fileList) {
//        String fileUrl = "";
//        String str = "";
//        String photoUrl = "";
//        for (int i = 0; i < fileList.size(); i++) {
//            fileUrl = uploadImg2Oss(fileList.get(i));
//            str = getImgUrl(fileUrl);
//            if (i == 0) {
//                photoUrl = str;
//            } else {
//                photoUrl += "," + str;
//            }
//        }
//        return photoUrl.trim();
//    }
//
//    /**
//     * 单个图片上传
//     *
//     * @param file
//     * @return
//     */
//    public String checkImage(MultipartFile file) {
//        String fileUrl = uploadImg2Oss(file);
//        String str = getImgUrl(fileUrl);
//        return str.trim();
//    }
}
