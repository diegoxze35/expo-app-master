package com.expo.expoapp.service;

import com.expo.expoapp.model.ExpoUser;
import com.expo.expoapp.request.UserRequest;
//import java.io.IOException;
import java.util.List;

public interface UserService {
    public List<ExpoUser> findAll();
    public ExpoUser save(UserRequest persona/*, MultipartFile profilePhoto*/) /*throws IOException*/;
}
