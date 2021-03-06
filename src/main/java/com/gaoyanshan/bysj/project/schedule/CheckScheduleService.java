package com.gaoyanshan.bysj.project.schedule;

import com.gaoyanshan.bysj.project.constant.Constant;
import com.gaoyanshan.bysj.project.entity.Task;
import com.gaoyanshan.bysj.project.entity.User;
import com.gaoyanshan.bysj.project.entity.UserTask;
import com.gaoyanshan.bysj.project.repository.TaskRepository;
import com.gaoyanshan.bysj.project.repository.UserTaskRepositiry;
import com.gaoyanshan.bysj.project.util.DateUtil;
import com.gaoyanshan.bysj.project.util.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * <pre>类名: CheckScheduleService</pre>
 * <pre>描述: 检查类任务调度</pre>
 * <pre>日期: 19-4-3 下午3:24</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Component
public class CheckScheduleService {
    private static final Logger logger = LoggerFactory.getLogger(CheckScheduleService.class);
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserTaskRepositiry userTaskRepositiry;
    @Autowired
    private MailUtil mailUtil;

    /**
     * 每天8点检查即将到期的任务并发送邮件通知
     */
    @Async
    @Scheduled(cron = "0 0 8 * * ? ")
    public void checkTaskDeadline(){
        logger.info(new Date()+":检查任务过期调度");
        Date startTime = DateUtil.getYesterdayBegin();
        Date endTime = DateUtil.getTomorrowBegin();
        List<Task> tasks = taskRepository.findAllByExpectedTimeBetween(startTime,endTime);
        Map<String,StringBuilder> deadlineTaskInfo = new HashMap<String, StringBuilder>();
        for (Task task : tasks){
            List<UserTask> userTasks = userTaskRepositiry.findAllByTaskEquals(task);
            for (UserTask userTask : userTasks){
                if (deadlineTaskInfo.get(userTask.getUser().getEmail()) == null){
                    deadlineTaskInfo.put(userTask.getUser().getEmail(),new StringBuilder(task.getTitle()));
                }else{
                    deadlineTaskInfo.get(userTask.getUser().getEmail()).append("\n"+userTask.getTask().getTitle());
                }
            }
            System.out.println(task.getId());
        }
        String subject = "任务通知";
        for (Map.Entry<String,StringBuilder> entry : deadlineTaskInfo.entrySet()) {
            String content = "<h2>以下任务即将到期，请查看：</h2></br>"+entry.getValue();
            mailUtil.sendHtmlEmail(subject,content,entry.getKey());
        }
    }
}
