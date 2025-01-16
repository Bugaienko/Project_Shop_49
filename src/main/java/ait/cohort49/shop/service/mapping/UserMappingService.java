package ait.cohort49.shop.service.mapping;

import ait.cohort49.shop.model.dto.UserRegisterDto;
import ait.cohort49.shop.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Sergey Bugaenko
 * {@code @date} 16.01.2025
 */

@Mapper(componentModel = "spring")
public interface UserMappingService {

    @Mapping(target = "id", ignore = true)
    User mapDtoToEntity(UserRegisterDto userRegisterDto);
}
