package design_pattern.visitor.scene02;

public class WeChatLogin implements Login {
    @Override
    public void accept(Visitor visitor) {
        System.out.println(visitor.getClass().getSimpleName()+"通过微信登录");
    }
}
