package com.se.imageservice.dto;

import com.se.imageservice.model.Image;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageDTO implements Serializable {
    private Long id;
    
    @NotNull(message = "required")
    private String fileURL;
    
    public static ImageDTO build(Image image) {
        return ImageDTO.builder()
                .id(image.getId())
                .fileURL(image.getFileURL())
                .build();
    }
}
