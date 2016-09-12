package com.ztw.web.controller.test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import com.ztw.basic.db.MongDataSourceConfig;
import com.ztw.basic.test.MongoUserService;
import com.ztw.basic.tools.ConfigTools;
import org.apache.commons.io.FileUtils;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by zsl-pc on 2016/9/8.
 */
@Controller
@RequestMapping(value = "test")
public class TestController {

    @Autowired
    ConfigTools configTools;

    @Autowired
    MongoUserService mongoUserService;

    @Autowired
    MongDataSourceConfig mongDataSourceConfig;

    //http://www.cnblogs.com/shihuc/p/5169418.html
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() throws Exception {
        System.out.println("=============="+configTools.getUploadPath());

        /*MongoUser user = new MongoUser();
        user.setUsername("zsl");
        user.setAge(28);
        user.setHometown("yunnan");
        user.setJob("coder");

        mongoUserService.addUser(user);*/

        MongoClient client = mongDataSourceConfig.buildMongo();
        MongoDatabase database = client.getDatabase("car-rent");
        GridFSBucket gridFSBucket = GridFSBuckets.create(database);

//        GridFSBucket gridFSBucket = GridFSBuckets.create(database, "files");

        InputStream streamToUploadFrom = new FileInputStream(new File("d:/temp/eureka-server-1.0-SNAPSHOT.jar"));

// Create some custom options
        GridFSUploadOptions options = new GridFSUploadOptions()
                .chunkSizeBytes(1024)
                .metadata(new Document("type", "presentation"));

        ObjectId fileId = gridFSBucket.uploadFromStream("mongodb-tutorial", streamToUploadFrom, options);

        System.out.println(fileId.toHexString());

        return "test/index";
    }

    @RequestMapping(value = "uploadFiles", method = RequestMethod.POST)
    public @ResponseBody String uploadFiles(@RequestParam("file")MultipartFile [] files) {
        String fileName = null;
        String msg = "";
        if(files!=null && files.length>0) {
            BufferedOutputStream bw = null;
            try {
                for(int i=0; i< files.length; i++) {
                    fileName = files[i].getOriginalFilename();
                    File outFile = new File(configTools.getUploadPath()+fileName);
                    FileUtils.copyInputStreamToFile(files[i].getInputStream(), outFile);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if(bw!=null) {bw.close();}
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "上传成功！";
    }
}
