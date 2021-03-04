package design_pattern.facade.scene01.inter;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>
 * 门面模式【Facade Pattern】
 * 定义 体格写信的人
 * </p>
 * @version:v1.0
 */
public interface LetterProcess {
    //首先要写信的内容
    public void writeContext(String context);

    //其次写信封
    public void fillEnvelope(String address);

    //把信放到信封里
    public void letterInotoEnvelope();

    //然后邮递
    public void sendLetter();
}
