package design_pattern.visitor.scene02;

public class WeiboLogin implements Login {
    @Override
    public void accept(Visitor visitor) {
        System.out.println(visitor.getClass().getSimpleName()+"通过微博登录");
    }
}
