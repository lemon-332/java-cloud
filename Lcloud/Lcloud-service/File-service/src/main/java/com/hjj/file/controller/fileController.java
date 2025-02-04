package com.hjj.file.controller;


import com.hjj.file.config.MinioConfig;
import com.hjj.file.util.MinioUtil;
import com.hjj.util.RespResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.minio.messages.Bucket;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/file")
public class fileController {
    private final MinioUtil minioUtil;

    private final MinioConfig prop;

    public fileController(MinioUtil minioUtil, MinioConfig prop) {
        this.minioUtil = minioUtil;
        this.prop = prop;
    }

    // 查看存储bucket是否存在
    @GetMapping("/bucketExists")
    public RespResult bucketExists(@RequestParam("bucketName") @Validated @NotBlank(message = "存储桶名不能为空") String bucketName) {
        if (minioUtil.bucketExists(bucketName)) {
            return RespResult.ok("存储bucket存在");
        } else {
            return RespResult.error("存储bucket不存在");
        }
    }

    // 创建存储bucket
    @GetMapping("/makeBucket")
    public RespResult makeBucket(@RequestParam("bucketName") @Validated @NotBlank(message = "存储桶名不能为空") String bucketName) {
        if (minioUtil.makeBucket(bucketName)) {
            return RespResult.ok("存储bucket创建成功");
        } else {
            return RespResult.error("存储bucket创建失败");
        }
    }

    // 删除存储bucket
    @GetMapping("/removeBucket")
    public RespResult removeBucket(String bucketName) {
        if (minioUtil.removeBucket(bucketName)) {
            return RespResult.ok("存储bucket删除成功");
        } else {
            return RespResult.error("存储bucket删除失败");
        }
    }

    // 获取全部bucket
    @GetMapping("/getAllBuckets")
    public RespResult<List<Bucket>> getAllBuckets() {
        List<Bucket> allBuckets = minioUtil.getAllBuckets();
        return RespResult.ok(allBuckets);
    }

    // 文件上传返回url
    @PostMapping("/upload")
    public RespResult upload(@RequestParam("file") MultipartFile file) {
        String objectName = minioUtil.upload(file);
        if (null != objectName) {
            return RespResult.ok(prop.getEndpoint() + "/" + prop.getBucketName() + "/" + objectName);
        }
        return RespResult.error();
    }

    // 图片/视频预览
    @GetMapping("/preview")
    public RespResult preview(@RequestParam("fileName") String fileName) {
        return RespResult.ok(minioUtil.preview(fileName));
    }

    // 文件下载
    @GetMapping("/download")
    public RespResult download(@RequestParam("fileName") String fileName, HttpServletResponse res) {
        minioUtil.download(fileName, res);
        return RespResult.ok();
    }

    // "删除文件", notes = "根据url地址删除文件"
    @PostMapping("/delete")
    public RespResult remove(String url) {
        String objName = url.substring(url.lastIndexOf(prop.getBucketName() + "/") + prop.getBucketName().length() + 1);
        minioUtil.remove(objName);
        return RespResult.ok(objName);
    }
}
