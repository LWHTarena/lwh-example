package design_pattern.facade.scene01.facade;

import design_pattern.facade.scene01.inter.LetterProcess;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p></p>
 * @version:v1.0
 */
public class Police {
    public void checkLetter(LetterProcess letterProcess) {
        System.out.println("Police.checkLetter()通过了监察");
    }
}
