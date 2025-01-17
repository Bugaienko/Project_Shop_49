package ait.cohort49.shop.service.mapping;

import ait.cohort49.shop.model.dto.CustomerDTO;
import ait.cohort49.shop.model.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Sergey Bugaenko
 * {@code @date} 17.01.2025
 */

@Mapper(componentModel = "spring", uses = CartMappingService.class)
public interface CustomerMapperService {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "true")
    Customer mapDtoToEntity(CustomerDTO dto);

    CustomerDTO mapEntityToDto(Customer entity);
}
