package design_pattern.adapter.scene01.inter.impl;

import design_pattern.adapter.scene01.inter.IUserInfo;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p></p>
 * @version:v1.0
 */
public class UserInfo implements IUserInfo {
    @Override
    public String getHomeAddress() {
        System.out.println("UserInfo.getHomeAddress()...员工家庭地址");
        return null;
    }

    @Override
    public String getHomeTelNumber() {
        System.out.println("UserInfo.getHomeTelNumber()...员工的家庭电话");
        return null;
    }

    @Override
    public String getJobPosition() {
        System.out.println("UserInfo.getJobPosition()...此人的职位是Boss");
        return null;
    }

    @Override
    public String getMobileNumber() {
        System.out.println("UserInfo.getMobileNumber()...此人的手机号是15999974038");
        return null;
    }

    @Override
    public String getOfficeTelNumber() {
        System.out.println("UserInfo.getOfficeTelNumber()...办公室的电话是110");
        return null;
    }

    @Override
    public String getUsername() {
        System.out.println("UserInfo.getUsername()...此人的姓名是李文浩");
        return null;
    }
}
