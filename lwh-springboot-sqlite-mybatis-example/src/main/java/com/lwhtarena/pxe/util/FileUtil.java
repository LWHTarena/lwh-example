package com.lwhtarena.pxe.util;

import com.lwhtarena.pxe.domain.Config;
import com.lwhtarena.pxe.domain.Net;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 创建、修改文件
 * @author liwh
 */
@Component
public class FileUtil {

    public static final Logger infoLog = LoggerFactory.getLogger(FileUtil.class);

//    @Value("${winhong-pxe.winStore}")
    public String winStore;

//    @Value("${winhong-pxe.winStore.sds_ins}")
    public String winStore_sds_ins;

//    @Value("winhong-pxe.winStore.shell")
    public String winStore_shell;

    public FileUtil(){
        init();
    }

    public void init() {
        List<File> list =getFiles(Default.DOWNLOAD_SERVER);
        for(File file:list){
            String name =file.getName();
            if(name.endsWith(".sh")){//脚本
                winStore_shell =name;
            }else if (name.endsWith(".gz")){//安装包
                winStore =name;
            }else{
                winStore_sds_ins =name;
            }

        }
    }

    /****
     * 检查字符串是否是"`/`结尾",没有即补上
     * @param path
     * @return
     */
    public static String complementarySlash(String path) {

        if(!path.endsWith("/")){
            path =path+"/";
        }
        return path;
    }

    /**
     *  把一个字符串中的大写转为小写  真选组
     */
    public static String exChange(String str){
        StringBuffer sb = new StringBuffer();
        if(str!=null){
            for(int i=0;i<str.length();i++){
                char c = str.charAt(i);
                if(Character.isUpperCase(c)){
                    sb.append(Character.toLowerCase(c));
                }else{
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 根据mac地址转化
     * @param type
     * @param lower
     * @return
     */
    public String toTsransform(String add,String type, String lower) {
        String file = add.replace(":",type);

        if(lower.equals("s")){//小写
            file ="01-"+file.toLowerCase();//+exChange(file);
        }else{//大写
            file ="01-"+file.toUpperCase();
        }
        file = file.replace(" ","");
        return file;
    }

    /**
     *
     * @param str [eth1,eth2，...]
     */
    public void arrayToAppend(String str,StringBuffer buf) {
        if (str.length()>2);{
            if(str.indexOf(",")!=-1){//存在
                String[] arrs = str.split(",");
                for(int i=0;i<arrs.length;i++){
                    buf.append("/usr/bin/echo \"").append(arrs[i]).append("\" >> /etc/firstboot.d/data/uninit_nic.conf");
                    buf.append(System.getProperty("line.separator"));
                }
            }else{//不包含，没有
                buf.append("/usr/bin/echo \"").append(str).append("\" >> /etc/firstboot.d/data/uninit_nic.conf");
                buf.append(System.getProperty("line.separator"));
            }
        }
    }
    /**
     *
     * @param str [eth1,eth2，...]
     */
    public String arrayToAppend(String str) {
        StringBuffer buf = new StringBuffer();
        if (str.length()>2);{
            if(str.indexOf(",")!=-1){//存在
                String[] arrs = str.split(",");
                for(int i=0;i<arrs.length;i++){
                    buf.append("/usr/bin/echo \"").append(arrs[i]).append("\" >> /etc/firstboot.d/data/uninit_nic.conf");
                    buf.append(System.getProperty("line.separator"));
                }
            }else{//不包含，没有
                buf.append("/usr/bin/echo \"").append(str).append("\" >> /etc/firstboot.d/data/uninit_nic.conf");
                buf.append(System.getProperty("line.separator"));
            }
        }
        return buf.toString();
    }
    /**
     * 读取默认文件
     * @param path /nfs/WinServer/winserver65-auto.ks
     * @param config
     * @return
     */
    public String readTemplate(String path,Config config,String nfsHttp) throws Exception {
        BufferedReader br =null;
        String line =null;
        StringBuffer buf =new StringBuffer();//
        /** 根据文件路径创建缓冲输入流**/
        try {
            br = new BufferedReader(new FileReader(path));//

            while ((line =br.readLine())!=null){
                    buf.append(line+"\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new Exception("readTemplate 文件不存在");
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("readTemplate IO异常");
        }finally {
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    br =null;
                    e.printStackTrace();
                }
            }
        }

        String content = buf.toString();
        content = content.replace("###host-ip###",nfsHttp);
        content = content.replace("###system-volume###",config.getSystemVolume());
        content = content.replace("###host-password###",config.getHostPwd());
        content = content.replace("###manage-network###",config.getManageNetwork());
        content = content.replace("###ram###",""+Integer.parseInt(config.getRam())*1024);
        String  networks = arrayToAppend(config.getStoreNetwork());
        content = content.replace("###networks###",networks);
        return content;
    }

    /**
     * @param path /var/lib/tftpboot/pxelinux.cfg
     * @param config
     * @param tftp
     * @return
     */
    public String readPXE(String path, Config config, String tftp) throws Exception {
        BufferedReader br =null;
        String line =null;
        StringBuffer buf =new StringBuffer();

        try {
            br =new BufferedReader(new FileReader(path));
            while ((line=br.readLine())!=null){
                if(line.startsWith("label WinServer")){
                    buf.append("label WinServer");
                    buf.append(System.getProperty("line.separator"));
                    buf.append("  kernel ./WinServer/vmlinuz");
                    buf.append(System.getProperty("line.separator"));
                    buf.append("  append initrd=./WinServer/initrd.img inst.gpt net.ifnames=0 biosdevname=0 ip=")
                            .append(config.getHostIp()).append("::") //ip
                            .append(config.getGateway()).append(":") //网关
                            .append(config.getNetmask()).append(":") //掩码
                            .append(config.getHostName()).append(":") //主机名
                            .append(config.getManageNetwork()).append(":none") //管理网卡名称 eth0
                            .append(" nameserver=").append(config.getHostIp())  //加多dns输入项，dns=ip 的值
                         .append(" quiet inst.ks=nfs:").append(tftp) //tftp 服务
                         .append(":/nfs/WinServer/")
                         .append(toTsransform(config.getMacAddr(),"-","b")); //应答文件
                }else if (line.startsWith("  kernel ./WinServer/vmlinuz")){
                    buf.append("");
                }else if(line.startsWith("  append initrd=")){
                    buf.append("");
                }else{
                    buf.append(line);
                }

                buf.append(System.getProperty("line.separator"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new Exception("readPXE 文件不存在");
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("readPXE IO异常");
        }finally {
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    br=null;
                    e.printStackTrace();
                }
            }
        }

        /** 根据文件路径创建缓冲输入流**/
        return buf.toString();
    }

    public static String readDHCPFile(String dhcpConf) throws Exception {
        String ip = null;
        BufferedReader br =null;
        String line =null;
        StringBuffer buf =new StringBuffer();
        try {
            br = new BufferedReader(new FileReader(dhcpConf));
            while ((line =br.readLine())!=null){
                if(line.trim().startsWith("next-server")){
                    ip =getIp(line);
                }
                if(line.startsWith("  append initrd=")){
                    ip =getIp(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new Exception("读取DHCP文件，文件不存在");
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("读取DHCP文件，IO异常");
        }finally {
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    br=null;
                    e.printStackTrace();
                }
            }
        }
        return ip;
    }

    /**
     * 提取ip地址
     * @param res
     * @return
     */
    private static String getIp(String res) {
        String ip =null;
        String regex = "\\d+\\.\\d+\\.\\d+\\.\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(res);
        if (matcher.find()) {
            ip =matcher.group(0);
        }else{
            System.out.println("no match");
        }
        return ip;
    }

    private static List<String> getIps(String res){
        List<String> list =new ArrayList<String>();
        String ip =null;
        String regex = "\\d+\\.\\d+\\.\\d+\\.\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(res);

        while (matcher.find()){
            ip =matcher.group(0);
            list.add(ip);
        }

        return list;
    }

    /**
     * 收集ip
     * @param path
     * @return
     */
    public static String readConfig(String path) throws Exception {
        File file =new File(path);
        String ip ="";

        if(file.exists()){ //文件存在
            if (path.equals(Default.DHCP_CONF)){ //dhcp 配置文件
                ip =readDHCPFile(Default.DHCP_CONF);
            }else{//default文件
                ip =readDHCPFile(Default.PXE_DEFAULT);
            }

        }
        return ip;

    }


    /**
     * 统一存放应答文件的目录：/nfs/WinServer
     * 该目录默认存在winserver65-auto.ks,如若没有使用系统的
     * 创建并修改ks应答文件
     * @param config
     * @param ip 应答服务器ip
     */
    public void createKS(Config config,String ip)throws  Exception{
        String nfs_url =Default.NFS_URL;
        nfs_url =complementarySlash(nfs_url);
        String fileURL =nfs_url+ toTsransform(config.getMacAddr(),"-","b"); //这里需要大写

        BufferedWriter bw =null;

        try {
            /**据文件路径创建缓冲输出流**/
            bw =new BufferedWriter(new FileWriter(fileURL));
            bw.write(readTemplate(Default.NFS_CFG_TEMPLATE,config,ip));
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("创建KS文件，IO异常");
        }finally {
            if(bw!=null){
                try {
                    bw.close();
                } catch (IOException e) {
                    bw = null;
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 统一存放应答文件的目录：/var/lib/tftpboot/pxelinux.cfg
     * 该目录默认存在default,如若没有使用系统的
     * 创建并修改pxe响应文件
     * @param config
     * @param ip pxe 服务器ip
     */
    public void createPXE(Config config,String ip)throws  Exception{
        String pxe_url =Default.PXE_URL;
        pxe_url =complementarySlash(pxe_url);
        String fileURL =pxe_url + toTsransform(config.getMacAddr(),"-","s"); //这里需要小写

        BufferedWriter bw =null;
        /**据文件路径创建缓冲输出流**/
        try {
            bw =new BufferedWriter(new FileWriter(fileURL));
            bw.write(readPXE(Default.PXE_DEFAULT,config,ip));
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("createPXE IO异常");
        }finally {
            if(bw!=null){
                try {
                    bw.close();
                } catch (IOException e) {
                    bw = null;
                    e.printStackTrace();
                }
            }
        }
    }

    /***
     * 收集dhcp的服务ip == tftp服务ip == nfs 服务ip
     * @return
     */
    public static String gatherServeIp() throws Exception {
        String ip ="";
        //读取dhcpd.conf

        String dhcp_ip =readConfig(Default.DHCP_CONF);

        //读取default
        String tftp_ip =readConfig(Default.PXE_DEFAULT);

        //比较两个的ip
        if(StringUtil.isNotEmpty(dhcp_ip)
                && StringUtil.isNotEmpty(tftp_ip)
                && dhcp_ip.equals(tftp_ip)){
            ip =dhcp_ip;
        }

        return ip;
    }


    @Deprecated
    public static boolean deleteAllFile(){
        boolean bool =true;
        File dir_01 =new File(Default.NFS_URL);  //ks应答文件存放的目录
        File dir_02 =new File(Default.PXE_URL);  //pxe 文件存放的目录
        if(dir_01.isDirectory() && dir_02.isDirectory()){
            String[] children_01 = dir_01.list();
            String[] children_02 = dir_02.list();

            /***********************
             * 清除ks应答文件存放的目录
             ***********************/
            for (int i=0;i<children_01.length;i++){
                if(children_01[i].startsWith("01-")){
                    new File(Default.NFS_URL,children_01[i]).delete();
                }
            }

            /***********************
             * 清除pxe 文件存放的目录
             ***********************/
            for (int i=0;i<children_02.length;i++){
                if(children_02[i].startsWith("01-")){
                    new File(Default.PXE_URL,children_02[i]).delete();
                }
            }
        }else{
            System.out.println("目录不存在");
            bool =false;
        }

        return bool;
    }

    /**
     * 根据任务，删除重置文件
     * 清除文件
     */
    public boolean deleteFile(List<Config> configs){
        boolean bool =true;
        File dir_01 =new File(Default.NFS_URL);  //ks应答文件存放的目录
        File dir_02 =new File(Default.PXE_URL);  //pxe 文件存放的目录

        if(dir_01.isDirectory() && dir_02.isDirectory()){
            for(Config conf:configs){
                String ks_temp =Default.NFS_URL+"/"+toTsransform(conf.getMacAddr(),"-","b"); //这里需要大写
                String pxe_temp =Default.PXE_URL+"/"+toTsransform(conf.getMacAddr(),"-","s"); //这里需要小写
                /***********************
                 * 清除ks应答文件存放的目录
                 ***********************/
                File ks_file = new File(ks_temp);
                if(ks_file.exists()){
                    infoLog.info("###### filename："+ks_file.getAbsolutePath());
                    ks_file.delete();
                }

                /***********************
                 * 清除ks应答文件存放的目录
                 ***********************/
                File pxe_file = new File(pxe_temp);
                if(pxe_file.exists()){
                    infoLog.info("###### filename>>>："+pxe_file.getAbsolutePath());
                    pxe_file.delete();
                }
            }

        }else{
            System.out.println("目录不存在");
            bool =false;
        }

        return bool;
    }

    public boolean deleteFile(Config conf) throws Exception{
        boolean bool =true;
        File dir_01 =new File(Default.NFS_URL);  //ks应答文件存放的目录
        File dir_02 =new File(Default.PXE_URL);  //pxe 文件存放的目录
        if(dir_01.isDirectory() && dir_02.isDirectory()){
            String ks_temp =Default.NFS_URL+"/"+toTsransform(conf.getMacAddr(),"-","b"); //这里需要大写
            String pxe_temp =Default.PXE_URL+"/"+toTsransform(conf.getMacAddr(),"-","s"); //这里需要小写

            /***********************
             * 清除ks应答文件存放的目录
             ***********************/
            File ks_file = new File(ks_temp);
            if(ks_file.exists()){
                infoLog.info("###### single filename："+ks_file.getAbsolutePath());
                ks_file.delete();
            }

            /***********************
             * 清除ks应答文件存放的目录
             ***********************/
            File pxe_file = new File(pxe_temp);
            if(pxe_file.exists()){
                infoLog.info("###### single filename>>>："+pxe_file.getAbsolutePath());
                pxe_file.delete();
            }
        }else{
            bool =false;
        }
        return bool;
    }

    /**
     * 修改dhcp文件
     * @param net
     */
    public void modifyLocalDHCP(Net net) {

        /****************注意先后顺序***************
         * 1、修改ifcfg-eth0   service network restart
         * 2、修改dhcpd.conf   service dhcpd restart
         * 3、修改default
         *****************************************/

        /*****************************************
         * 1、修改ifcfg-eth0
         *
         *****************************************/
        List eth0Set = new ArrayList();
        eth0Set.add("DEVICE=\"eth0\"");
        eth0Set.add("BOOTPROTO=\"static\"");
        eth0Set.add("IPADDR=\""+net.getIp()+"\"");
        eth0Set.add("NETMASK=\""+net.getNetmask()+"\"");
        eth0Set.add("GATEWAY=\""+net.getGateway()+"\"");
        eth0Set.add("NM_CONTROLLED=\"yes\"");
        eth0Set.add("ONBOOT=\"yes\"");
        eth0Set.add("TYPE=\"Ethernet\"");
        eth0Set.add("IPV4_FAILURE_FATAL=yes");
        eth0Set.add("IPV6INIT=no");
        eth0Set.add("NAME=\"System eth0\"");
        this.rewrite(eth0Set,Default.LOCAL_NET_ETH0);

        /*****************************************
         * 2、修改dhcpd.conf
         *****************************************/
        List set = new ArrayList();
        set.add("#");
        set.add("# DHCP Server Configuration file.");
        set.add("#   see /usr/share/doc/dhcp*/dhcpd.conf.sample");
        set.add("#   see 'man 5 dhcpd.conf'");
        set.add("#");
        set.add("ddns-update-style interim;");
        set.add("allow booting;");
        set.add("allow bootp;");
        set.add("ignore client-updates;");
        set.add("set vendorclass = option vendor-class-identifier;");
        set.add("option pxe-system-type code 93 = unsigned integer 16;");
        set.add("subnet "+net.getSubnet()+" netmask "+net.getNetmask()+" {");
        set.add("    option routers  "+net.getGateway()+";");  //网关
        set.add("    option domain-name-servers 8.8.8.8;");
        set.add("    option subnet-mask "+net.getNetmask()+";");
        set.add("    range dynamic-bootp "+net.getDhcpLower()+" "+net.getDhcpUpper()+";"); //DHCP范围
        set.add("   default-lease-time 21600;");
        set.add("    max-lease-time 43200;");
        set.add("    next-server "+net.getIp()+";"); //tftp服务ip
        set.add("    filename   \"pxelinux.0\";");
        set.add("}");
        this.rewrite(set,Default.DHCP_CONF);

        /***************************
         * 3、修改default
         ***************************/
        List defaultSet =new ArrayList();
        defaultSet.add("serial 0 115200");
        defaultSet.add("default WinServer");
        defaultSet.add("prompt 1");
        defaultSet.add("timeout 50");
        defaultSet.add("");
        defaultSet.add("label WinServer");
        defaultSet.add("  kernel ./WinServer/vmlinuz");
        defaultSet.add("  append initrd=./WinServer/initrd.img inst.gpt net.ifnames=0 biosdevname=0 quiet inst.repo=nfs:"+net.getIp()+":/nfs/WinServer");
        this.rewrite(defaultSet,Default.PXE_DEFAULT);

        /**
         * 1、重启 ifcfg-eth0   service network restart
         * 2、重启 dhcpd.conf   service dhcpd restart
         */
        JavaShellUtil javaShellUtil = new JavaShellUtil();
        try {
            boolean bool =javaShellUtil.executeShell("service network restart");
            if(bool){
                javaShellUtil.executeShell("service dhcpd restart");
            }
        } catch (IOException e) {
            infoLog.error("重启 service network restart 失败 or service dhcpd restart 失败");
            e.printStackTrace();
        }

    }

    /**
     * 重写文件
     * @param set
     * @param path
     */
    private void rewrite(List set, String path) {
        Iterator iterator = set.iterator();
        FileWriter fw = null;
        BufferedWriter writer = null;
        try {
            fw = new FileWriter(path);
            writer = new BufferedWriter(fw);
            while(iterator.hasNext()){
                writer.write(iterator.next().toString());
                writer.newLine();//换行
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * 收集当前虚拟机信息
     * @return
     */
    public Net gatherInfo() {
        Net net =new Net();

        BufferedReader br =null;
        String line =null;
        StringBuffer buf =new StringBuffer();//
        try {
            br = new BufferedReader(new FileReader(Default.DHCP_CONF));//
            while ((line =br.readLine())!=null){
                if(line.startsWith("subnet")){
                    List<String> arr =getIps(line);
                    if(arr.size()==2){
                        net.setSubnet(arr.get(0));   //子网
                        net.setNetmask(arr.get(1));  //掩码
                    }
                }
                if(line.trim().startsWith("option routers")){
                    String str =getIp(line);
                    net.setGateway(str);   //网关

                }
                if(line.trim().startsWith("range dynamic-bootp")){
                    List<String> arr01 =getIps(line);
                    if(arr01.size()==2){
                        net.setDhcpLower(arr01.get(0));   //dhcp
                        net.setDhcpUpper(arr01.get(1));  //掩码
                    }
                }
                if(line.trim().startsWith("next-server")){
                    String str =getIp(line);
                    net.setIp(str);// ip
                }
            }
        } catch (FileNotFoundException e) {
            infoLog.info(">>>>>/etc/dhcp/dhcpd.conf not exist ");
            e.printStackTrace();
        } catch (IOException e) {
            infoLog.info(">>>>> 文件流出错... ");
            e.printStackTrace();
        }

        return net;
    }

    /*******************
     * 获取该目录下的文件
     * @param path
     * @return
     */
    public static List<File> getFiles(String path) {
        List<File> list =new ArrayList<File>();
        File dir = new File(path);
        File[] files =dir.listFiles();

        if(files!=null){
            for (File file:files){
                list.add(file);

            }
        }
        return list;
    }

    /*获取该目录下的文件夹*/
    public static List<File> getFolders(String path){

        infoLog.info("/*************** 该目录下："+path+" 的文件******************");
        List<File> filelist =new ArrayList<File>();
        File dir = new File(path);
        File[] files =dir.listFiles();
        if(StringUtil.isNotNull(files)){
            for(File file:files){
                if(file.isDirectory()){//判断是文件还是文件夹
                    File[] childs =file.listFiles();
                    if(childs.length > 0){
                        filelist.add(file);
                        infoLog.info("**   "+file.getAbsolutePath());
                    }
                }
            }
        }
        infoLog.info("/*********************************************************");
        return filelist;
    }
}
