package com.gaoyanshan.bysj.project.controller;

import com.gaoyanshan.bysj.project.dto.ProjectDTO;
import com.gaoyanshan.bysj.project.entity.User;
import com.gaoyanshan.bysj.project.exception.SystemException;
import com.gaoyanshan.bysj.project.response.Response;
import com.gaoyanshan.bysj.project.service.DynamicContentService;
import com.gaoyanshan.bysj.project.service.ProjectService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <pre>类名: ProjectController</pre>
 * <pre>描述: 项目控制层</pre>
 * <pre>日期: 19-3-21 下午8:21</pre>
 * <pre>作者: gaoyanshan</pre>
 */
@CrossOrigin(origins = "http://0.0.0.0:8888")
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private DynamicContentService dynamicContentService;

    /**
     * 通过id获得相关项目的接口
     * @param id
     * @return
     */
    @GetMapping("/item")
    public Response getProject(@RequestParam("id") int id){
        return Response.success(projectService.getProject(id));
    }

    /**
     * 获得当前用户相关的项目
     * @return
     */
    @GetMapping("/projects")
    public Response getProjects(){
        Subject subject = SecurityUtils.getSubject();
        User user = null;
        try{
            user = (User) subject.getPrincipal();
        }catch (ClassCastException e){
            throw new SystemException("类型转化出错："+e.getMessage());
        }
        return Response.success(projectService.getProjectsByUserId(user.getId()));
    }

    /**
     * 新增项目
     * @return
     */
    @PostMapping("/add")
    public Response addProject(@RequestBody ProjectDTO dto){
        Subject subject = SecurityUtils.getSubject();
        User user = null;
        try{
            user = (User) subject.getPrincipal();
        }catch (ClassCastException e){
            throw new SystemException("类型转化出错："+e.getMessage());
        }

        return Response.success(projectService.addProject(dto,user));
    }

    /**
     * 删除项目
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Response deleteProject(@PathVariable("id") int id){
        projectService.deletedProject(id);
        return Response.success("true");
    }

    /**
     * 更新项目内容
     * @param id
     * @param map
     * @return
     */
    @PutMapping("/{id}")
    public Response updateProject(@PathVariable("id") int id,@RequestBody Map<String,Object> map){
        System.out.println(id);
        System.out.println(map);
        projectService.updateProject(id,map);
        return Response.success("true");
    }

    @RequiresAuthentication
    @GetMapping("/all")
    public Response getAllProjects(@RequestParam(name = "page",defaultValue = "1")int page,
                                   @RequestParam(name = "size",defaultValue = "6")int size){
        return Response.success(projectService.getAllProject(page,size));
    }


    @GetMapping("/recent")
    public Response getRecentPid(){
        Subject subject = SecurityUtils.getSubject();
        User user = null;
        try{
            user = (User) subject.getPrincipal();
        }catch (ClassCastException e){
            throw new SystemException("类型转化出错："+e.getMessage());
        }
        return Response.success(projectService.getRecentProjrctId(user));
    }

    @PutMapping("/recent/{id}")
    public Response setRecentPid(@PathVariable("id")int pId){
        Subject subject = SecurityUtils.getSubject();
        User user = null;
        try{
            user = (User) subject.getPrincipal();
        }catch (ClassCastException e){
            throw new SystemException("类型转化出错："+e.getMessage());
        }
        projectService.setRecentProjectId(user,pId);
        return Response.success(true);
    }

    @GetMapping("/many/userId")
    public Response getProjectsByUserId(@RequestParam("userId") int userId){
        return Response.success(projectService.getProjectsByUserId(userId));
    }

    @GetMapping("/delete/user")
    public Response deleteUserOfProject(@RequestParam("userId")int userId,
                                        @RequestParam("pId")int projectId){
        return Response.success(projectService.deleteUserOfProject(userId,projectId));
    }

    /**
     * 新增项目的人员
     * @param userId
     * @param projectId
     * @return
     */
    @GetMapping("/add/user")
    public Response addUserOfProject(@RequestParam("userId")int userId,
                                     @RequestParam("pId")int projectId){
        return Response.success(projectService.addUserOfProject(userId,projectId));
    }


    @GetMapping("/dynamics")
    public Response getDynamics(@RequestParam("pId")int projectId,
                                @RequestParam("page") int page,
                                @RequestParam("size") int size){

        return  Response.success(dynamicContentService.getDynamicContentByProjectId(page,size,projectId));
    }

}
