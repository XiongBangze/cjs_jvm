package opencv.test;

import java.io.File;
import java.io.IOException;

public class TestOCR {

    public static void main(String[] args) {
        String path = "D:\\play\\cjs_jvm\\src\\main\\java\\opencv\\Images\\image.jpg";
        PictureManage pictureManage = new PictureManage(path); //对图片进行处理
        pictureManage.imshow();
        try {
            String valCode = new OCR().recognizeText(new File("D:\\play\\cjs_jvm\\src\\main\\java\\opencv\\Images\\xintu.jpg"), "jpg");//jpg是图片格式
            System.out.println("图片中文字为："+"\n"+valCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
