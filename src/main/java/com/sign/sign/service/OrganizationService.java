package com.sign.sign.service;

import com.sign.sign.entity.Organization;

public interface OrganizationService {

    /**
     * 创建组织
     * @param organization 组织实体(id为空)
     */
    void createOrganization(Organization organization);

    /**
     * 通过组织但id查找组织
     * @param id 组织id
     * @return 组织实体
     */
    Organization findById(int id);

    boolean deleteOrganization(int id);
}
