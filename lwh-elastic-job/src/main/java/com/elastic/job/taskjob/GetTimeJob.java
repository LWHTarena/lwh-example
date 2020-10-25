package com.elastic.job.taskjob;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liwh
 * @Title: GetTimeJob
 * @Package com.elastic.job.taskjob
 * @Description:
 * @Version 1.0.0
 * @date 2020/10/25 23:33
 */
@Component
public class GetTimeJob implements SimpleJob {

    private static final Logger LOG = LoggerFactory.getLogger(GetTimeJob.class.getName()) ;

    private static final SimpleDateFormat format =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;

    @Override
    public void execute(ShardingContext shardingContext) {
        LOG.info("Job Name:"+shardingContext.getJobName());
        LOG.info("Local Time:"+format.format(new Date()));
    }
}

