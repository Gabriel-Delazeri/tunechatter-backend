package com.delazeri.music.dtos.auth;

import java.time.LocalDateTime;

public record LoginResponseDto(String token, LocalDateTime expiresAt) {
}
