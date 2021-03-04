package design_pattern.visitor.scene02;

/**
 * 登录接口
 */
public interface Login {

    /**
     * 对于登录业务而言，访问者是被接受的
     * @param visitor
     */
    void accept(Visitor visitor);
}
