package com.etma.gateway.domain.scope.requests;

import com.etma.shared.core.validations.NoHtml;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ScopePaginationRequest {
    @NoHtml
    private String searchTerm;

    @Min(1)
    @Max(100)
    private Integer perPage = 20;

    @Min(value = 1)
    private Integer page = 1;
}
