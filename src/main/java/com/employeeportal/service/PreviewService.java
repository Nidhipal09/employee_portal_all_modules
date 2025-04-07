package com.employeeportal.service;

import com.employeeportal.model.OtherDetails;
import com.employeeportal.model.Preview;

import java.util.List;

public interface PreviewService {
    Preview savePreview(Preview preview);
    List<Preview> getAllPreview();
    Preview getPreviewById(Long previewId);
    Preview updatePreviewById(Long previewId, Preview preview);
    Preview deletePreviewById(Long previewId);
}
