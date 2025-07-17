package com.marcosetm.user_management.mapper;

import com.marcosetm.user_management.dto.AccountUpdateDto;
import com.marcosetm.user_management.model.Account;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AccountUpdateMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAccountFromDto(AccountUpdateDto accountUpdateDto, @MappingTarget Account account);
}
