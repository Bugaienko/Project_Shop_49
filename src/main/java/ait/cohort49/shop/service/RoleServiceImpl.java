package ait.cohort49.shop.service;

import ait.cohort49.shop.model.entity.Role;
import ait.cohort49.shop.repository.RoleRepository;
import ait.cohort49.shop.service.interfaces.RoleService;
import org.springframework.stereotype.Service;

/**
 * @author Sergey Bugaenko
 * {@code @date} 16.01.2025
 */

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleUser() {
        // Получить роль "USER" из БД по title
        return roleRepository.findRoleByTitle("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Database doesn't contain ROLE_USER"));
    }
}
