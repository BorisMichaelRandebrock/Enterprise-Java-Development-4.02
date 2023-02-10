package com.randebrock.EnterpriseJavaDevelopment42.controller.dto;

import com.randebrock.EnterpriseJavaDevelopment42.enums.EmployeeStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class EmployeeStatusDTO {
    @NotNull(message = "The status cannot be empty")
    private EmployeeStatus status;

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }
}
