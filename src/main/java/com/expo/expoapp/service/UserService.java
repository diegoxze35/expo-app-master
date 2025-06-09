package com.expo.expoapp.service;

import com.expo.expoapp.dto.ExpoUserDTO;
import com.expo.expoapp.request.UserRequest;
import java.util.List;

public interface UserService {
    public List<ExpoUserDTO> findAll();
    public ExpoUserDTO save(UserRequest persona/*, MultipartFile profilePhoto*/) /*throws IOException*/;
}
