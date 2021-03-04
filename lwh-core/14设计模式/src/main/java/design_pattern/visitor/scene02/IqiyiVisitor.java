package design_pattern.visitor.scene02;

public class IqiyiVisitor implements Visitor {

    @Override
    public void visit(Login login) {
        System.out.println("爱奇艺访问");
        login.accept(this);
    }
}
