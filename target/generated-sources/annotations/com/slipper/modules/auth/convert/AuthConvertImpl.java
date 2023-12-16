package com.slipper.modules.auth.convert;

import com.slipper.modules.user.entity.UserEntity;
import com.slipper.modules.user.model.dto.LoginUserDTO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-16T10:45:35+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_291 (Oracle Corporation)"
)
public class AuthConvertImpl implements AuthConvert {

    @Override
    public LoginUserDTO convert(UserEntity bean) {
        if ( bean == null ) {
            return null;
        }

        LoginUserDTO loginUserDTO = new LoginUserDTO();

        loginUserDTO.setId( bean.getId() );
        loginUserDTO.setNickname( bean.getNickname() );
        loginUserDTO.setAvatar( bean.getAvatar() );
        loginUserDTO.setSex( bean.getSex() );
        loginUserDTO.setOnline( bean.getOnline() );
        loginUserDTO.setStatus( bean.getStatus() );
        loginUserDTO.setOpenId( bean.getOpenId() );

        return loginUserDTO;
    }
}
