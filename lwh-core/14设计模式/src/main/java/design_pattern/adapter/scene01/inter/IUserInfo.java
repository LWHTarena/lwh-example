package design_pattern.adapter.scene01.inter;

import java.util.Map;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p></p>
 * @version:v1.0
 */
public interface IUserInfo {
    String getHomeAddress();

    String getHomeTelNumber();

    String getJobPosition();

    String getMobileNumber();

    String getOfficeTelNumber();

    String getUsername();

    /**
     * @author：liwenhao
     * @Date:2017/2/18
     * @description:<p>外系统的人员信息</p>
     * @version:v1.0
     */
    interface IOuterUser {
        //基本信息，比如名称、性别，手机号等
        public Map getUserBaseInfo();

        //工作区域信息
        public Map getUserOfficeInfo();

        //用户的家庭信息
        public Map getUserHomeInfo();
    }
}
