package com.elastic.job.controller;

import com.elastic.job.service.TaskJobService;
import com.elastic.job.taskjob.GetTimeJob;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liwh
 * @Title: TaskJobController
 * @Package com.elastic.job.controller
 * @Description:
 * @Version 1.0.0
 * @date 2020/10/25 23:37
 */
@RestController
public class TaskJobController {

    @Resource
    private TaskJobService taskJobService ;

    @RequestMapping("/addJob")
    public String addJob(@RequestParam("cron") String cron, @RequestParam("jobName") String jobName,
                         @RequestParam("shardCount") Integer shardCount,
                         @RequestParam("shardItem") String shardItem) {
        taskJobService.addTaskJob(jobName, new GetTimeJob(), cron, shardCount, shardItem);
        return "success";
    }
}
