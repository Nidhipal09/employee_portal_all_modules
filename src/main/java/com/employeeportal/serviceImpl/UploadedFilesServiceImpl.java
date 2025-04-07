package com.employeeportal.serviceImpl;

import com.employeeportal.config.AdminProperties;
import com.employeeportal.exception.BadRequestException;
import com.employeeportal.model.UploadedFiles;
import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.repository.UploadedFilesRepository;
import com.employeeportal.repository.PrimaryDetailsRepository;
import com.employeeportal.service.UploadedFilesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@Slf4j
public class UploadedFilesServiceImpl implements UploadedFilesService {

    private final UploadedFilesRepository filesRepository;
    private final AdminProperties adminProperties;
    private final PrimaryDetailsRepository primaryDetailsRepo;

    @Autowired
    public UploadedFilesServiceImpl(UploadedFilesRepository filesRepository, AdminProperties adminProperties, PrimaryDetailsRepository primaryDetailsRepo) {
        this.filesRepository = filesRepository;
        this.adminProperties = adminProperties;
        this.primaryDetailsRepo = primaryDetailsRepo;
    }

    @Override
    public UploadedFiles uploadFile(MultipartFile file) throws IOException {
        PrimaryDetails primaryDetails = new PrimaryDetails();
        try {
            if(file.isEmpty()){
                throw new BadRequestException("File is empty please upload file.");
            }

            if (!java.nio.file.Files.exists(Paths.get(adminProperties.getUploadDirectory()).resolve(file.getOriginalFilename()))) {
                java.nio.file.Files.copy(file.getInputStream(), Paths.get(adminProperties.getUploadDirectory()).resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());

                // Generate the file URL
                String pathName = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/images/")
                        .path(fileName)
                        .toUriString();

                // Save file record to database
                UploadedFiles uploadedFile = new UploadedFiles(pathName);
                filesRepository.save(uploadedFile);

                // Return the uploaded file details
                return uploadedFile;
            } else {
                throw new BadRequestException("File already exists.");
            }
        } catch (IOException e) {
            throw new RuntimeException("File can't be uploaded. " + e.getMessage());
        }
    }
}
