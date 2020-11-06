package com.sign.sign.controller;

import com.sign.sign.entity.Organization;
import com.sign.sign.service.impl.OrganizationServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class OrganizationController {

    private final OrganizationServiceImpl organizationService;

    public OrganizationController(OrganizationServiceImpl organizationService) {
        this.organizationService = organizationService;
    }

    /**
     * 创建组织、子组织
     * 在创建子组织时需要同时父组织id
     * 创建顶级组织时父组织id设为0
     * @return 返回操作结果
     */
    @PostMapping("/organization")
    public Map<?,?> createOrganization(ResponseBody body){
        Organization organization= new Organization();
        organization.setName("523");
        organization.setNumber("523");
        organization.setParent(0);
        organization.setType(1);
        organizationService.createOrganization(organization);
        return null;
    }

    /**
     * 删除组织
     * @param id 组织id
     * @return 返回操作结果
     */
    @DeleteMapping("/organization/{id}")
    public Map<?,?> deleteOrganization(@PathVariable Integer id){
        organizationService.deleteOrganization(id);
        return null;
    }

    /**
     * 获取组织信息
     * @param id 组织id
     * @return 返回请求数据与操作结果
     */
    @GetMapping("/organization/{id}")
    public Map<?,?> getOrganization(@PathVariable Integer id){
        System.out.println(id);
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("data",organizationService.findById(id));
        return map;
    }

    /**
     * 邀请成员加入组织(子组织)
     * 一个用户可以加入多个顶级组织
     * 但一个用户在一个顶级组织中只能属于一个子组织
     * @return 返回操作结果
     */
    @PostMapping("/member")
    public Map<?,?> invitationMember(@PathVariable Integer id){
        return null;
    }

    @DeleteMapping("/member")
    public Map<?,?> removeMember(){
        return null;
    }

}
