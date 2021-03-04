package design_pattern.visitor.scene02;

/**
 * 访问者接口
 */
public interface Visitor {

    /**
     * 对于访问者而言，登录是访问的对象
     * @param login
     */
    void visit(Login login);
}
