package com.hsmap.yuezhihui;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class FileUtils {
    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    //获取流文件
    public static File multipartFileToFile(MultipartFile multipartFile) {
        try {
            File toFile = null;
            if (multipartFile.equals("") || multipartFile.getSize() <= 0) {
                multipartFile = null;
            } else {
                InputStream ins = null;
                ins = multipartFile.getInputStream();
                toFile = new File(multipartFile.getOriginalFilename());
                inputStreamToFile(ins, toFile);
                ins.close();
            }
            return toFile;
        } catch (IOException e) {
            logger.error("", e);
        }
        return null;
    }

    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取文件名的后缀
     *
     * @param originalFilename 文件文件
     * @return 后缀名
     */
    public static String getExtension(String originalFilename) {
        return getExtension(originalFilename, null);
    }

    /**
     * 获取文件名的后缀
     *
     * @param originalFilename 文件文件
     * @param contentType      文件ContentType
     * @return 后缀名
     */
    public static String getExtension(String originalFilename, String contentType) {
        String extension = "";
        if (originalFilename != null) {
            int extensionPos = originalFilename.lastIndexOf(46);
            int lastUnixPos = originalFilename.lastIndexOf(47);
            int lastWindowsPos = originalFilename.lastIndexOf(92);
            int lastSeparator = Math.max(lastUnixPos, lastWindowsPos);
            int index = lastSeparator > extensionPos ? -1 : extensionPos;
            extension = index == -1 ? "" : originalFilename.substring(index + 1);
        }
        //如果extension为空，根据contentType获取类型后缀
        if (contentType != null && extension == null || "".equals(extension)) {
            extension = getExtensionByContentType(contentType);
        }
        return extension;
    }

    public static final String IMAGE_PNG = "image/png";

    public static final String IMAGE_JPG = "image/jpg";

    public static final String IMAGE_JPEG = "image/jpeg";

    public static final String IMAGE_BMP = "image/bmp";

    public static final String IMAGE_GIF = "image/gif";

    public static String getExtensionByContentType(String prefix) {
        switch (prefix) {
            case IMAGE_PNG:
                return "png";
            case IMAGE_JPG:
                return "jpg";
            case IMAGE_JPEG:
                return "jpeg";
            case IMAGE_BMP:
                return "bmp";
            case IMAGE_GIF:
                return "gif";
            default:
                return "";
        }
    }

    /**
     * 获得指定文件的byte数组
     *
     * @param filePath 文件绝对路径
     * @return
     */
    public static byte[] file2Byte(String filePath) {
        File file = new File(filePath);
        return file2Byte(file);
    }

    /**
     * 获得指定文件的byte数组
     *
     * @param file 文件绝对路径
     * @return
     */
    public static byte[] file2Byte(File file) {
        ByteArrayOutputStream bos = null;
        BufferedInputStream in = null;
        try {
            if (!file.exists()) {
                throw new FileNotFoundException("file not exists");
            }
            bos = new ByteArrayOutputStream((int) file.length());
            in = new BufferedInputStream(new FileInputStream(file));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (Exception e) {
            logger.error("", e);
            return null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (Exception e) {
                logger.error("", e);
            }
        }
    }

    /**
     * 根据byte数组，生成文件
     *
     * @param bfile    文件数组
     * @param filePath 文件存放路径
     * @param fileName 文件名称
     */
    public static void byte2File(byte[] bfile, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && !dir.isDirectory()) {//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                logger.error("", e);
            }

        }
    }

}
