package design_pattern.flyweight.scene03;

public class StringInterningDemo {
    public static void main(String[] args) {

        String value =new String("Hello");//在Heap

        String newValue =value.intern();//放置常量池

        System.out.println(newValue);
    }
}
