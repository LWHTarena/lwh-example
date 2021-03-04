package design_pattern.strategy.scene03;

/**
 * 省份
 */
public interface Provice {

    /**
     * 是否是我的任务
     * @return
     */
    boolean isMyJob(String type);

    void doSomeThing(String type);
}
