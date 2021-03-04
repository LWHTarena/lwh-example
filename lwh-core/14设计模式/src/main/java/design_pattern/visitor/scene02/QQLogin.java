package design_pattern.visitor.scene02;

public class QQLogin  implements Login {
    @Override
    public void accept(Visitor visitor) {
        System.out.println(visitor.getClass().getSimpleName()+"通过QQ登录");
    }
}
