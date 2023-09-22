package com.delazeri.music.dtos.albums;

import java.util.UUID;

public record ReviewRequestDto (UUID albumId, String comment, double rating){
}
