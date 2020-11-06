package com.sign.sign.service.impl;

import com.sign.sign.dao.OrganizationRepository;
import com.sign.sign.entity.Organization;
import com.sign.sign.service.OrganizationService;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository repository;

    public OrganizationServiceImpl(OrganizationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createOrganization(Organization organization) {
        repository.save(organization);
    }

    @Override
    public Organization findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteOrganization(int id) {
        repository.deleteById(id);
        return false;
    }
}
