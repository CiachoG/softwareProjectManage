package com.gaoyanshan.bysj.project.repository;

import com.gaoyanshan.bysj.project.entity.Project;
import com.gaoyanshan.bysj.project.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <pre>类名: TaskRepository</pre>
 * <pre>描述: task的dao</pre>
 * <pre>日期: 19-3-25 下午8:23</pre>
 * <pre>作者: gaoyanshan</pre>
 */
@Repository
public interface TaskRepository extends JpaRepository<Task,Integer>,JpaSpecificationExecutor<Task> {

    @Modifying
    @Query("update Task t set t.deleted = 1 where t.id = :id")
    void deleteById(@Param("id") int id);

    @Query("select t from Task t where t.id=:id")
    Task findOneById(@Param("id") int taskId);

    List<Task> findAllByProjectId(int id);

    @Modifying
    @Query("update Task t set t.isDone= :status where t.id=:id")
    void updateStatus(@Param("id") int taskId, @Param("status") int status);
}