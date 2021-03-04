package design_pattern.strategy.scene02.tools;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>为了提高系统的灵活性和可扩展性，我们将具体策略类
 *  的类名存储在配置文件中，并通过工具类XMLUtil来读取配置文件并反射生成对象</p>
 * @version:v1.0
 */
public class XMLUtil {
    //该方法用于从XML配置文件中提取具体类类名，并返回一个实例对象
    public static Object getBean() {
        try {
            //创建文档对象
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(new File("C:\\WorkSpaces\\idea\\基础\\16 设计模式\\src\\main\\java\\design_pattern\\_01策略模式\\场景二\\tools\\XMLUtil.java"));

            //获取包含类名的文本节点
            NodeList nl = doc.getElementsByTagName("className");
            Node classNode=nl.item(0).getFirstChild();
            String cName=classNode.getNodeValue();

            //通过类名生成实例对象并将其返回
            Class c=Class.forName(cName);
            Object obj=c.newInstance();
            return obj;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
