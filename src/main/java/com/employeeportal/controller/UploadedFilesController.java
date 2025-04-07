package com.employeeportal.controller;
import com.employeeportal.model.UploadedFiles;
import com.employeeportal.model.GeneralResponse;
import com.employeeportal.service.UploadedFilesService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Api(value = "Files", tags = "Files")
@RestController
@RequestMapping("/api/files")
@Slf4j
@Validated
@CrossOrigin(origins = "*")
public class UploadedFilesController {
    private final UploadedFilesService filesService;

    public UploadedFilesController(UploadedFilesService filesService) {
        this.filesService = filesService;
    }

    @PostMapping("/uploadFile")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public GeneralResponse<UploadedFiles> uploadFile(@RequestParam("file") MultipartFile upload) throws IOException {
        // Save the file and get the URL
        UploadedFiles uploadedFile = filesService.uploadFile(upload);
        // Return response with file URL
        return new GeneralResponse<>(true, "Upload file Successfully", uploadedFile);
    }
}
