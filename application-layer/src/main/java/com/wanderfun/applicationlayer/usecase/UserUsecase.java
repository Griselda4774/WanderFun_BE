package com.wanderfun.applicationlayer.usecase;

import com.wanderfun.applicationlayer.dto.users.MiniUserDto;

public interface UserUsecase {
    MiniUserDto getMiniSelfInfo(String accessToken);
}