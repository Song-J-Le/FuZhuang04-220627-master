package com.cn.wanxi.servlet.user;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/code")
public class CodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /*
        验证码的思路？
        1.创建一个空白图片
        2.对当前图片画一个背景颜色
        3.在图片中间画字符（验证码）
        4.对验证码进行模糊状态
         */
        // 定义验证码的位数
        int size = 5;
        // 1.验证码图片的生成
        // 定义图片的宽度和高度
        int width = (int) Math.ceil(size * 25);
        int height = 50;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图片的上下文,拿到画笔
        Graphics gr = image.getGraphics();
        // 设定图片背景颜色
        // 随机数生成类
        Random random = new Random();
        gr.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
//        gr.setColor(Color.white);
        gr.fillRect(0, 0, width, height);//将矩形进行颜色填充
        // 设定图片边框
        gr.setColor(Color.GRAY);
        gr.drawRect(0, 0, width - 1, height - 1);


        //2. 定义变量保存生成的验证码
//        String vCode = randomCode(size);
        String vCode = getNumber(size);

        // 保存生成的5位验证码
        req.getSession().setAttribute("vCode", vCode);

        // 设置字体，画验证码
        gr.setColor(randomColor());
        gr.setFont(randomFont());
        gr.drawString(vCode, 10, 42);

        //3. 画十条干扰线

        for (int i = 0; i < 25; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            gr.setColor(randomColor());
            gr.drawLine(x1, y1, x2, y2);
        }

        // 图像生效
        gr.dispose();
        // 4.输出到页面
        ImageIO.write(image, "JPEG", resp.getOutputStream());

    }

    private String getNumber(int size) {
        String str = "qwertyupasdfghjklzxcvbnm0123456789";
        int length = str.length();//得到字符串的长度
        Random random = new Random();

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int index = random.nextInt(length);//随机数，代表str
//            str.charAt(index)根据索引拿到str里面对应的字符
            s.append(str.charAt(index));
        }
        return s.toString();
    }

    // 生成随机的颜色
    private Color randomColor() {
        int red = r.nextInt(100);
        int green = r.nextInt(100);
        int blue = r.nextInt(100);
        return new Color(red, green, blue);
    }

    private String[] fontNames = {"宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312"};
    private Random r = new Random();

    // 生成随机的字体
    private Font randomFont() {
        int index = r.nextInt(fontNames.length);
        String fontName = fontNames[index];// 生成随机的字体名称
        int style = r.nextInt(4);
        int size = r.nextInt(10) + 28; // 生成随机字号, 24 ~ 28
        return new Font(fontName, style, size);
    }

    /**
     * 生成验证码的字符
     *
     * @param size
     * @return
     */
    private String randomCode(int size) {
        // 随机数生成类
        Random random = new Random();
        StringBuilder vCode = new StringBuilder();
        char c;
        // 产生验证码
        for (int i = 0; i < size; i++) {
            // 产生一个26以内的随机整数
//            Math.random() 产生随机数取值访问位0-1之间的小数
//            random.nextInt(int) 取小于整数参数里面的随机数
            int number = random.nextInt(26);
            // 如果生成的是偶数，则随机生成一个数字
//            number % 2 取双
            if (number % 2 == 0) {
//               c= (char)('0'+'9');
//                c = (char) ('0' + (char) ((int) (Math.random() * 10)));
                c = (char) (random.nextInt(9) + 48);
                // 如果生成的是奇数，则随机生成一个字母
            } else {
//                c = (char) ((char) ((int) (Math.random() * 26)) + 'A');
                c = (char) (random.nextInt(26) + 97);
            }
            vCode.append(c);
        }
        return vCode.toString();
    }
}
