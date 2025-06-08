package com.expo.expoapp.response;

import java.time.LocalDate;
import java.util.List;

public record TeamResponse(
    String title,
    String description,
    LocalDate expositionDate,
	String name,
	List<String> members
) {
}
