package com.lwhtarena.pxe.util.ebs;


import com.alibaba.fastjson.JSONObject;

/**
 * @author： liwh
 * @Date: 2017/4/1.
 * @Description：<p>策略类</P>
 */
public class Contex {
	
	 //用来获取配置IP地址的key
    public static final String ESB_SYSTEM_IP_KEY = "ESB_SYSTEM_IP_KEY";
    

    private IStrategy strategy;

    public Contex(IStrategy strategy){
        this.strategy =strategy;
    }

    
    public String put(String ip,String url,String postBody) throws Exception{
        return this.strategy.postRest(ip,url,postBody);
    }


    /*校验网络是否连通*/
    public boolean checkIp(String ip){
        boolean bool = false;
        try {
            bool = this.strategy.checkIp(ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bool;
    }

    public static void main(String[] args) {
        JSONObject authJson = new JSONObject();
        authJson.put("ipaddr","192.168.222.181");
//        authJson.put("hostid", "node0003");
//        authJson.put("license", "SA6Y6766-BTC6BDUY-DCY597W5-LUWJ2R5T");
        Contex contex =new Contex(new EBSrest());

        //String string = contex.put(ip, EBSrestUrl.LIC_QUERY,null);

        String authStr = null;
        try {
            authStr = contex.put("192.168.222.181", "/api/v1/sds/system/progress/get", authJson.toString());
            System.out.println(authStr);
            org.json.JSONObject ebsAuthJson = new org.json.JSONObject(authStr);
            int success = ebsAuthJson.getInt("success");
            if(success == 1){
                //TODO 更新进度到内存中
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
