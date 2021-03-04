package design_pattern.visitor.scene02;

public class VisitorDemo {
    public static void main(String[] args) {

        //创建优酷访问者
        Visitor visitor =new YoukuVisitor();

        //微博登录
        Login login =new WeiboLogin();

        //优酷访问者 -> 微博登录
        visitor.visit(login);

        //创建爱奇艺的访问者
        visitor =new IqiyiVisitor();

        //爱奇艺访问者 -> 微博登录
        visitor.visit(login);

        //QQ登录
        Login qqLogin =new QQLogin();
        //爱奇艺访问者 --> QQ登录
        visitor.visit(qqLogin);
    }
}
