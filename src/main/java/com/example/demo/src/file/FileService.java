package com.example.demo.src.file;
import com.example.demo.src.file.model.File;
import com.example.demo.src.file.model.FileDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class FileService {

    private FileRepository fileRepository;

    public FileService(FileRepository fileRepository){
        this.fileRepository=fileRepository;
    }

    @Transactional
    public Long saveFile(FileDto fileDto){
        return fileRepository.save(fileDto.toEntity()).getId();
    }

    @Transactional
    public FileDto getFile(Long id){
        File file=fileRepository.findById(id).get();

        FileDto fileDto=FileDto.builder()
                .id(id)
                .originFilename(file.getOriginFilename())
                .filename(file.getFilename())
                .filepath(file.getFilepath())
                .build();
        return fileDto;

    }

}