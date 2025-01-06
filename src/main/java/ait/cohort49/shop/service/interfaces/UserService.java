package ait.cohort49.shop.service.interfaces;

import ait.cohort49.shop.model.dto.UserRegisterDto;

/**
 * @author Sergey Bugaenko
 * {@code @date} 06.01.2025
 */

public interface UserService {

    void register(UserRegisterDto userRegisterDto);
}
