package ait.cohort49.shop.service.interfaces;

import ait.cohort49.shop.model.entity.Role;

/**
 * @author Sergey Bugaenko
 * {@code @date} 16.01.2025
 */

public interface RoleService {

    // Метод получения "роли по умолчанию" для новых пользователей
    Role getRoleUser();
}
