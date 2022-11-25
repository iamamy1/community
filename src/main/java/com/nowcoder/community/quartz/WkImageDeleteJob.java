package com.nowcoder.community.quartz;

//import com.qiniu.common.QiniuException;
//import com.qiniu.common.Zone;
//import com.qiniu.storage.BucketManager;
//import com.qiniu.storage.Configuration;
//import com.qiniu.util.Auth;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;

public class WkImageDeleteJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(WkImageDeleteJob.class);

    @Value("${wk.image.storage}")
    private String wkImageStorage;
//    @Value("${qiniu.key.access}")
//    private String accessKey;
//    @Value("${qiniu.key.secret}")
//    private String secretKey;
//    @Value("${qiniu.bucket.share.name}")
//    private String shareBucketName;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        File[] files = new File(wkImageStorage).listFiles();
        if(files == null || files.length == 0){
            logger.info("没有WK图片，任务取消!");
            return;
        }

        for(File file:files){
            // 删除一分钟之前创建的图片
            if(System.currentTimeMillis() - file.lastModified() > 60*1000){
                logger.info("删除WK图片：" + file.getName());

//                // 删除七牛云上的图片
//                Auth auth = Auth.create(accessKey,secretKey);
//                BucketManager bucketManager = new BucketManager(auth,new Configuration(Zone.zone0()));
//                try {
//                    String key = file.getName().substring(0,file.getName().lastIndexOf("."));
//                    bucketManager.delete(shareBucketName,key);
//                    logger.info("成功删除WK图片：" + file.getName());
//                } catch (QiniuException e) {
//                    logger.info("删除WK图片失败：" + file.getName());
//                }

                // 删除服务端的图片
                file.delete();
            }
        }
    }
}
