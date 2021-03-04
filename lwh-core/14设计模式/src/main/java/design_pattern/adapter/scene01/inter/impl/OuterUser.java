package design_pattern.adapter.scene01.inter.impl;

import design_pattern.adapter.scene01.inter.IOuterUser;

import java.util.HashMap;
import java.util.Map;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p></p>
 * @version:v1.0
 */
public class OuterUser implements IOuterUser {
    @Override
    public Map getUserBaseInfo() {
        HashMap baseInfoMap =new HashMap();

        baseInfoMap.put("username", "这个员工叫混世魔王...");
        baseInfoMap.put("mobileNumber", "这个人的电话是...");
        return baseInfoMap;
    }

    @Override
    public Map getUserHomeInfo() {
        HashMap homeInfo =new HashMap();

        homeInfo.put("homeTelNumber", "员工的家庭电话...");
        homeInfo.put("homeAddress", "员工的家庭地址");
        return homeInfo;
    }

    @Override
    public Map getUserOfficeInfo() {
        HashMap officeInfo =new HashMap();

        officeInfo.put("jobPoistion", "此人的职位是...");
        officeInfo.put("办公室电话", "员工的办公室电话是...");
        return officeInfo;
    }
}
