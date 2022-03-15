package com.hsmap.yuezhihui.images;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


public class VerifyUtil {
    public final Logger LOGGER = LoggerFactory.getLogger(VerifyUtil.class);

    private int width = 100;// 图片宽
    private int height = 25;// 图片高
    private int lineSize = 40;// 干扰线数量
    private Random random = new Random();

    /**
     * 获得字体
     */
    private Font getFont() {
        return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
    }

    /**
     * 获得颜色
     */
    private Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /**
     * 生成随机图片
     */
    public void getRandcode(HttpServletResponse response, String randomString) {
        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        width = 15*randomString.length();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();// 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        g.fillRect(0, 0, width, height);//图片大小
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));//字体大小
        g.setColor(getRandColor(110, 133));//字体颜色
        // 绘制干扰线
        for (int i = 0; i <= lineSize; i++) {
            drowLine(g);
        }

        // 绘制随机字符
        drowString(g, randomString);
        g.dispose();
        try {
            // 将内存中的图片通过流动形式输出到客户端
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (Exception e) {
        }

    }

    /**
     * 绘制字符串
     */
    private void drowString(Graphics g, String randomString) {

        LOGGER.info("randomString:{}", randomString);
        for (int i = 0; i < randomString.length(); i++) {


            String rand = randomString.substring(i,i+1);
            g.setFont(getFont());
            g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
                    .nextInt(121)));
            g.translate(random.nextInt(3), random.nextInt(3));
            g.drawString(rand, 13 * i, 16);
        }
    }

    /**
     * 绘制干扰线
     */
    private void drowLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }


}
