package com.gaoyanshan.bysj.project.repository;

import com.gaoyanshan.bysj.project.entity.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <pre>类名: UserTaskRepositiry</pre>
 * <pre>描述: 用户任务信息表dao</pre>
 * <pre>日期: 19-3-25 下午7:07</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Repository
public interface UserTaskRepositiry extends JpaRepository<UserTask,Integer>{

    @Query("select ut from UserTask ut where ut.user.id = :id")
    List<UserTask> getAllByUserId(@Param("id") int id);

    @Modifying
    @Query(value = "insert into user_task(user_id,task_id) values(?1,?2)",nativeQuery = true)
    void saveOneRecord(int userId, int taskID);
}

