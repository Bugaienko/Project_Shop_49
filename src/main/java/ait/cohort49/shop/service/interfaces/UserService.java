package ait.cohort49.shop.service.interfaces;

import ait.cohort49.shop.model.dto.UserRegisterDto;
import ait.cohort49.shop.model.entity.User;

/**
 * @author Sergey Bugaenko
 * {@code @date} 06.01.2025
 */

public interface UserService {

    void register(UserRegisterDto userRegisterDto);

    String confirmEmail(String code);
}
