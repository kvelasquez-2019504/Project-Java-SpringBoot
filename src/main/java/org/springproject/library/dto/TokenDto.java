package org.springproject.library.dto;

import java.util.Date;

public record TokenDto(
        String token,
        Date expirationDate) {
}
