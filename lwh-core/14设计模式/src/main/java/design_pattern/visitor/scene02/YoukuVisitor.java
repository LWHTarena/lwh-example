package design_pattern.visitor.scene02;

public class YoukuVisitor implements Visitor {
    @Override
    public void visit(Login login) {
        System.out.println("优酷访问者");
        login.accept(this);
    }
}
