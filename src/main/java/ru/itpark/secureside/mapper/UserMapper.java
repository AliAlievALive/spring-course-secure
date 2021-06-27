package ru.itpark.secureside.mapper;

import org.mapstruct.Mapper;
import ru.itpark.secureside.dto.User;
import ru.itpark.secureside.entity.UserEntity;

@Mapper
public interface UserMapper {

    User toDto(UserEntity entity);

    UserEntity toEntity(User dto);

}
