package com.expo.expoapp.request;

import java.time.LocalDate;

public record ProjectRequest(String title, String description, LocalDate expositionDate) {
}
