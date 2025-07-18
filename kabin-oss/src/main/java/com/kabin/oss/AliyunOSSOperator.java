package com.kabin.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class AliyunOSSOperator {

    @Autowired
    private AliyunOSSProperties ossProperties;

    public String upload(byte[] content, String originalFilename) throws Exception {
        String endpoint = ossProperties.getEndpoint();
        String bucketName = ossProperties.getBucketName();

        // 获取访问凭证（需确保设置了环境变量 OSS_ACCESS_KEY_ID 和 OSS_ACCESS_KEY_SECRET）
        EnvironmentVariableCredentialsProvider credentialsProvider =
                CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        // 创建按年月分目录的 OSS 路径
        String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        String newFileName = UUID.randomUUID().toString() + suffix;
        String objectName = dir + "/" + newFileName;

        // 创建 OSS 客户端
        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);

        // 设置元信息
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentDisposition("inline");

        // ✅ 动态设置 Content-Type
        String contentType = getContentType(suffix);
        metadata.setContentType(contentType);

        try {
            // 上传带元信息的文件
            ossClient.putObject(bucketName, objectName,
                    new ByteArrayInputStream(content), metadata);
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        // 返回图片 URL（适用于公共读 bucket）
        return endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + objectName;
    }

    private String getContentType(String suffix) {
        switch (suffix) {
            case ".jpg":
            case ".jpeg":
                return "image/jpeg";
            case ".png":
                return "image/png";
            case ".gif":
                return "image/gif";
            case ".bmp":
                return "image/bmp";
            case ".svg":
                return "image/svg+xml";
            case ".pdf":
                return "application/pdf";
            default:
                return "application/octet-stream"; // 默认通用二进制流
        }
    }


}
