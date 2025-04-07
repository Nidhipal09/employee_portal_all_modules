package com.employeeportal.service;

import com.employeeportal.model.UploadedFiles;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadedFilesService {

    UploadedFiles uploadFile(MultipartFile upload) throws IOException;

}
