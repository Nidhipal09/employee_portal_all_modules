package com.employeeportal.service;

import com.employeeportal.model.RelativeInfo;

import java.util.List;

public interface RelativeInfoService {
    List<RelativeInfo> getAllRelatives();
    RelativeInfo getRelativeById(Long relativeId);
    RelativeInfo saveRelative(RelativeInfo relativeInfo);
    RelativeInfo updateRelative(Long relativeId, RelativeInfo relativeInfo);
    RelativeInfo deleteRelativeInfoById(Long relativeId);
}
