package com.expo.expoapp.service;

import com.expo.expoapp.dto.TeamDTO;
import java.util.List;

public interface TeamService {

	public List<TeamDTO> findAll();
	public TeamDTO save(String teamName, String matriculate);

}
