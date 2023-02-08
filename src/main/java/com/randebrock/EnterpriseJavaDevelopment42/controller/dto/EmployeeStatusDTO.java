package com.randebrock.EnterpriseJavaDevelopment42.controller.dto;

import jakarta.validation.constraints.NotEmpty;

public class EmployeeStatusDTO {
    @NotEmpty(message = "The status cannot be empty")
    private Enum status;

    public Enum getStatus() {
        return status;
    }

    public void setStatus(Enum status) {
        this.status = status;
    }

}
