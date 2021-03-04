package design_pattern.adapter.scene01.inter.impl;

import design_pattern.adapter.scene01.inter.IUserInfo;

import java.util.Map;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p></p>
 * @version:v1.0
 */
public class OuterUserInfo extends OuterUser implements IUserInfo {
    private Map baseInfo =super.getUserBaseInfo();
    private Map homeInfo =super.getUserHomeInfo();
    private Map officeInfo =super.getUserOfficeInfo();

    @Override
    public String getHomeAddress() {
        String homeAddress =(String) this.homeInfo.get("homeAddress");
        System.out.println(homeAddress);
        return homeAddress;
    }

    @Override
    public String getHomeTelNumber() {
        String homeTelNumber =(String) this.homeInfo.get("homeTelNumber");
        System.out.println(homeTelNumber);
        return homeTelNumber;
    }

    @Override
    public String getJobPosition() {
        String jobPosition =(String) this.officeInfo.get("jobPoistion");
        System.out.println(jobPosition);
        return jobPosition;
    }

    @Override
    public String getMobileNumber() {
        String mobileNumber =(String) this.baseInfo.get("mobileNumber");
        System.out.println(mobileNumber);
        return mobileNumber;
    }

    @Override
    public String getOfficeTelNumber() {
        String 	officeTelNumber =(String) this.officeInfo.get("officeTelNumber");
        System.out.println(officeTelNumber);
        return officeTelNumber;
    }

    @Override
    public String getUsername() {
        String userName =(String) this.baseInfo.get("username");
        System.out.println(userName);
        return userName;
    }
}
