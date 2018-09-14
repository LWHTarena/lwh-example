package com.lwhtarena.pxe.util;

/**
 * 默认目录和默认文件
 */
public class Default {

    /**
     * ks应答文件存放的目录
     */
    public static final String NFS_URL ="/nfs/WinServer";

    /***
     * ks 默认应答文件或模板文件
     */
    public static final String NFS_CFG_TEMPLATE ="/nfs/WinServer/winserver65-auto.ks";

    /**
     * pxe 文件存放的目录
     */
    public static final String PXE_URL ="/var/lib/tftpboot/pxelinux.cfg";

    /**
     * pxe 默认文件
     */
    public static final String PXE_DEFAULT ="/var/lib/tftpboot/pxelinux.cfg/default";

    /**
     * dhcp 配置文件
     */
    public static final String DHCP_CONF ="/etc/dhcp/dhcpd.conf";


    /**
     * 服务虚拟机的配置文件
     */
    public static final String LOCAL_NET_ETH0 ="/etc/sysconfig/network-scripts/ifcfg-eth0";

    /*******************
     * 存放winStore的目录
     *******************/
    public static final String DOWNLOAD_SERVER ="/var/www/html";
//    public static final String DOWNLOAD_SERVER ="D:/var/www/html";

}
