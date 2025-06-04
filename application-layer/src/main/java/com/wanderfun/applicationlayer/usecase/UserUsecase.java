package com.wanderfun.applicationlayer.usecase;

import com.wanderfun.applicationlayer.dto.users.MiniUserDto;
import com.wanderfun.applicationlayer.dto.users.UserCreateDto;
import com.wanderfun.applicationlayer.dto.users.UserDto;

public interface UserUsecase {
    MiniUserDto getMiniSelfInfo(String accessToken);
    UserDto getSelfInfo(String accessToken);
    UserDto updateSelfInfo(String accessToken, UserCreateDto userCreateDto);
}