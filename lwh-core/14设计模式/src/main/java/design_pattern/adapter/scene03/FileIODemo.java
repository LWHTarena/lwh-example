package design_pattern.adapter.scene03;

import java.io.*;

public class FileIODemo {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {


        //目前拥有的对象
        InputStream is =new FileInputStream("abc.txt");

        //需要的对象
        Reader reader =new InputStreamReader(is,"utf-8");

        System.out.println(reader);
    }

}
