package design_pattern.decorator.scene03;

import java.io.*;

public class FileIODemo {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        //被装饰者
        InputStream is =null;

        //装饰者
        FilterInputStream filterInputStream =
                new DataInputStream(is);

        //DataInputStream <- FilterInputStream <- InputStream (存在层次关系)

    }

}
