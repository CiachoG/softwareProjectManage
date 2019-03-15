package com.gaoyanshan.bysj.project.repository;

import com.gaoyanshan.bysj.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <pre>类名: UserRepository</pre>
 * <pre>描述: user的dao层</pre>
 * <pre>日期: 19-3-15 下午2:44</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
