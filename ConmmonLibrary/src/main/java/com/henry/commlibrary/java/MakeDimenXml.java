package com.henry.commlibrary.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * java代码：创建适配不同分辨率手机的配置文件
 * 320x480
 * 360x640
 * 480x640
 * 480x800
 * 480x854
 * 540x960
 * 600x1024
 * 720x1184
 * 720x1196
 * 720x1280
 * 768x1024
 * 768x1280
 * 800x1280
 * 1080x1812
 * 1080x1920
 * 1200x1920
 * 1440x2560
 * 其他未适配到的机型，单位为1dp
 * <p>
 * Creator: ldns-dt-1745
 * Time: 17-4-18 11:22
 * Email:heneymark@gmail.com
 * Description: 创建demins文件，适配不同分辨率手机
 */
public class MakeDimenXml {

    private final static String rootPath = "res/values-{0}x{1}/";
    private final static String commRootPath = "$HOME/values/";

    private final static float dw = 320f;

    private final static float dh = 480f;

    private final static String WTemplate = "<dimen name=\"x{0}\">{1}px</dimen>\n";
    private final static String HTemplate = "<dimen name=\"y{0}\">{1}px</dimen>\n";

    private final static String CommWTemplate = "<dimen name=\"x{0}\">{1}dp</dimen>\n";
    private final static String CommHTemplate = "<dimen name=\"y{0}\">{1}dp</dimen>\n";

    /**
     * 构造字符串，并写入文件
     *
     * @param w
     * @param h
     */
    public static void makeString(int w, int h) {

        boolean isComm = isComm(w, h);

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        stringBuffer.append("<resources>");
        if (isComm) {
            for (int i = 1; i <= 320; i++) {
                stringBuffer.append(CommWTemplate.replace("{0}", i + "").replace("{1}", i + ""));
            }
        } else {
            float cellw = w / dw;
            for (int i = 1; i < 320; i++) {
                stringBuffer.append(WTemplate.replace("{0}", i + "").replace("{1}", change(cellw * i) + ""));
            }
            stringBuffer.append(WTemplate.replace("{0}", "320").replace("{1}", w + ""));
        }
        stringBuffer.append("</resources>");


        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        stringBuffer2.append("<resources>");
        if (isComm) {
            for (int i = 1; i <= 480; i++) {
                stringBuffer2.append(CommHTemplate.replace("{0}", i + "").replace("{1}", i + ""));
            }
        } else {
            float cellh = h / dh;
            for (int i = 1; i < 480; i++) {
                stringBuffer2.append(HTemplate.replace("{0}", i + "").replace("{1}", change(cellh * i) + ""));
            }
            stringBuffer2.append(HTemplate.replace("{0}", "480").replace("{1}", h + ""));
        }
        stringBuffer2.append("</resources>");


        String path = isComm ? commRootPath : rootPath.replace("{0}", h + "").replace("{1}", w + "");
        File rootFile = new File(path);
        if (!rootFile.exists()) {
            rootFile.mkdirs();
        }
        File layxFile = new File(path + "demin_lay_x.xml");
        File layyFile = new File(path + "demin_lay_y.xml");

        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(layxFile));
            pw.print(stringBuffer.toString());
            pw.close();
            pw = new PrintWriter(new FileOutputStream(layyFile));
            pw.print(stringBuffer2.toString());
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * 判断是否为通用，h为0，或w为0，表示通用
     *
     * @param w
     * @param h
     * @return
     */
    public static boolean isComm(int w, int h) {
        if (w == 0 || h == 0)
            return true;
        return false;
    }

    /**
     * @param num
     * @return
     */
    public static float change(float num) {
        int tmp = (int) (num * 100);
        return tmp / 100f;
    }

    public static void main(String[] args) {

        makeString(0, 0);
        makeString(320, 480);
        makeString(360, 640);
        makeString(480, 640);
        makeString(480, 800);
        makeString(480, 854);
        makeString(540, 960);
        makeString(600, 1024);
        makeString(720, 1184);
        makeString(720, 1196);
        makeString(720, 1280);
        makeString(768, 1024);
        makeString(768, 1280);
        makeString(800, 1280);
        makeString(1080, 1812);
        makeString(1080, 1920);
        makeString(1200, 1920);
        makeString(1440, 2560);
        makeString(1536, 2048);
    }


}
