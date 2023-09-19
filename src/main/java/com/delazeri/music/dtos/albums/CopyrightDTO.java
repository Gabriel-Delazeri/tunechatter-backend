package com.delazeri.music.dtos.albums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CopyrightDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;

    private String text;

    private String type;
}
