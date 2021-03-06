package com.elastic.job.quickstart;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.elastic.job.model.FileCustom;
import com.elastic.job.taskjob.FileBackupJob;

/**
 * @author liwh
 * @Title: JobMain
 * @Package com.elastic.job.quickstart
 * @Description:
 * @Version 1.0.0
 * @date 2020/10/29 02:07
 */
public class JobMain {

    /**zookeeper端口**/
    static final int ZOOKEEPER_PORT =2181;
    /**zookeeper连接字符串 localhost：2181**/
    static final String ZOOKEEPER_CONNECTION_STRING ="localhost:"+ZOOKEEPER_PORT;
    /**定时任务命名空间**/
    static final String JOB_NAMESPACE ="elastic-job-example-java";

    public static void main(String[] args) {
        /**生成测试文件**/
        generateTestFiles();

        /**配置zookeeper**/
        CoordinatorRegistryCenter registryCenter =setUpRegistryCenter();

        /**启动任务**/
        startJob(registryCenter);
    }


    /**配置并启动任务**/
    private static void startJob(CoordinatorRegistryCenter registryCenter) {
        /**创建JobCoreConfiguration**/
        JobCoreConfiguration jobCoreConfiguration =
                JobCoreConfiguration.newBuilder("files-job", "0/3 * * * * ?", 1).build();

        /**创建SimpleJobConfiguration**/
        SimpleJobConfiguration simpleJobConfiguration =
                new SimpleJobConfiguration(jobCoreConfiguration, FileBackupJob.class.getCanonicalName());

        /**启动任务**/
        new JobScheduler(registryCenter,
                LiteJobConfiguration.newBuilder(simpleJobConfiguration).overwrite(true).build()).init();
    }

    /**
     * zk的配置及创建注册中心
     */
    private static CoordinatorRegistryCenter setUpRegistryCenter() {
        /**注册中心配置**/
        ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration(ZOOKEEPER_CONNECTION_STRING, JOB_NAMESPACE);

        /**减少zk的超时时间**/
        zookeeperConfiguration.setSessionTimeoutMilliseconds(100);

        /**创建注册中心**/
        CoordinatorRegistryCenter center = new ZookeeperRegistryCenter(zookeeperConfiguration);
        center.init();
        return center;
    }

    /**生成测试环境**/
    private static void generateTestFiles() {
        for(int i=1;i < 11;i++){
            FileBackupJob.files.add(new FileCustom(String.valueOf(i+10),"文件"+(i+10),"text","content"+(i+10)));
            FileBackupJob.files.add(new FileCustom(String.valueOf(i+20),"文件"+(i+20),"image","content"+(i+20)));
            FileBackupJob.files.add(new FileCustom(String.valueOf(i+30),"文件"+(i+30),"radio","content"+(i+30)));
            FileBackupJob.files.add(new FileCustom(String.valueOf(i+40),"文件"+(i+40),"video","content"+(i+40)));
        }
    }
}
