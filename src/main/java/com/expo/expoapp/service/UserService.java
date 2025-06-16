package com.expo.expoapp.service;

import com.expo.expoapp.dto.ExpoUserDTO;
import com.expo.expoapp.dto.NewUserDTO;
import com.expo.expoapp.request.UserRequest;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    public NewUserDTO save(UserRequest persona, MultipartFile profilePhoto) throws IOException;
    public ExpoUserDTO findById(String id);
}
