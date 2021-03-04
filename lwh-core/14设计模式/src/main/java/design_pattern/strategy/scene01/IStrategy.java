package design_pattern.strategy.scene01;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>首先定一个策略接口，这是诸葛亮老人家给赵云的三个锦囊妙计的接口</p>
 * @version:v1.0
 */
public interface IStrategy {
    /**
     * 每个锦囊妙计都是一个科执行的算法
     */
    public void operate();
}
