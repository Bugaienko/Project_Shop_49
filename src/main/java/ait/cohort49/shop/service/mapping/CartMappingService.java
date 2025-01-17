package ait.cohort49.shop.service.mapping;

import ait.cohort49.shop.model.dto.CartDTO;
import ait.cohort49.shop.model.entity.Cart;
import org.mapstruct.Mapper;

/**
 * @author Sergey Bugaenko
 * {@code @date} 17.01.2025
 */


@Mapper(componentModel = "spring", uses = CustomerMapperService.class)
public interface CartMappingService {

    Cart mapDtoToEntity(CartDTO dto);

    CartDTO mapEntityToDto(Cart entity);
}
