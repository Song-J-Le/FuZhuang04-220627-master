package com.cn.wanxi.util;

import com.cn.wanxi.model.ImgModel;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

public class UpLoad {
    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "upload";

    // 上传配置
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    public static ImgModel load(HttpServletRequest req) {
//前端上传的文件有可能是多个文件，但是不确定是几个文件
//        List<File> list=req.getParameter("aa");
        ImgModel imgModel = new ImgModel();
//        封装model
        // 检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(req)) {
            imgModel.setSrc("Error: 表单必须包含 enctype=multipart/form-data");
            return imgModel;
        }

        // 配置上传参数:配置临时目录，缓存
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
//        factory.setSizeThreshold(MEMORY_THRESHOLD);
//         设置临时存储目录
//        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

//        解析文件
        ServletFileUpload upload = new ServletFileUpload(factory);

        // 设置最大文件上传值
//        upload.setFileSizeMax(MAX_FILE_SIZE);

        // 设置最大请求值 (包含文件和表单数据)
//        upload.setSizeMax(MAX_REQUEST_SIZE);

        // 中文处理
        upload.setHeaderEncoding("UTF-8");

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录:将数据保存到具体目录
        String uploadPath = req.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;


        // 如果目录不存在则创建
//        File uploadDir = new File(uploadPath);
//        if (!uploadDir.exists()) {
//            uploadDir.mkdirs();
//        }
        createDirectory(uploadPath);

        try {

            // 解析请求的内容提取文件数据,将前端传递过来的数据强制转换为文件集合
            List<FileItem> formItems = upload.parseRequest(req);

            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
//                        拿到文件名称
                        String fileName = new File(item.getName()).getName();
//                        如果名字一样，则上传不成功，所以，需要对名字进行重新命名（让名字唯一）
//                        1.可以加随机数Math.random()
//                        2.可以加UUID.randomUUID()
//                        3.可以加时间戳 System.currentTimeMillis()
//                        1234565754325---9_8hh4.jpg
                        fileName = System.currentTimeMillis() + "---" + fileName;
//                        将刚刚的地址和文件名称进行拼接
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        // 在控制台输出文件的上传路径
                        System.out.println(filePath);
                        // 保存文件到硬盘
                        item.write(storeFile);
                        imgModel.setSrc("/" + UPLOAD_DIRECTORY + "/" + fileName);
//                        将item的值再存入到另一个地址
//                        E:\wanxi\step-4\canyin04-220627\out\artifacts\canyin04_220627_war_exploded
//                        E:\wanxi\step-4\canyin04-220627\web
//                        String aa="E:/wanxi/step-4/canyin04-220627/web";
                        String newPath = uploadPath.split("out")[0] + "/web/" + UPLOAD_DIRECTORY;
                        createDirectory(newPath);
                        File newFile = new File(newPath, fileName);
//                        item.write(newFile);
//                        item可能不能再次赋值
//                        那能不能将刚刚out里面的文件复制到web目录下？
                        copy(storeFile, newFile);

                    } else {

                    }
                }
            }
        } catch (Exception ex) {

        }
        return imgModel;
    }

    private static void createDirectory(String path) {

        File uploadDir = new File(path);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
    }

    /**
     * 复制文件：必要条件是什么？
     * 两个地址
     */
    private static void copy(File oldPath, File newPath) {
        try {
            OutputStream outputStream = new FileOutputStream(newPath);
            InputStream inputStream = new FileInputStream(oldPath);
            int read = 0;
            while ((read = inputStream.read()) != -1) {
                outputStream.write(read);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
