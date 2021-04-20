package com.example.demo.src.video.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VideoDto {

    private Long id;
    private Long fileId;

    public Video toEntity(){
        Video video =Video.builder()
                .id(id)
                .fileId(fileId)
                .build();
        return video;
    }

    @Builder
    public VideoDto(Long id, Long fileId) {
        this.id = id;
        this.fileId = fileId;
    }


}