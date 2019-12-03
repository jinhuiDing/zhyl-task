package com.zhyl;

import org.junit.Test;
import java.io.File;

/**
 * @ClassName TestFileFormatVerify
 * @Description 测试FileFormatVerify类
 * @Author sun
 * Date 2019/5/8 14:57
 * @Version 1.0
 **/
public class TestFileFormatVerify {

    /**
     * @Description 测试传入参数后缀是否有篡改
     * @Param []
     * @return void
     **/
    @Test
    public void TestSuffixReg(){
        FileFormatVerify reg = new FileFormatVerify();
        File file = new File("C:\\Users\\ding\\Desktop\\等保修改.xlsx");

        if (file.isFile() && file.exists()){

            boolean b = reg.suffixVerify(file);
            System.out.println(b);

        }

    }

    /**
 
     * @Description 查看传入文件格式的前几位字符
     * @Param []
     * @return void
     **/
     @Test
    public void CheckFileByte8(){
        FileFormatVerify reg = new FileFormatVerify();
        File file = new File("C:\\Users\\ding\\Desktop\\等保修改.xlsx");

        byte[] bytes = reg.inputStream2ByteArray(file);
        String s = reg.bytesToHexString(bytes);
        System.out.println(s);
    }
}


