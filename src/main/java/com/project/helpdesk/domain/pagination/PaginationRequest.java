package com.project.helpdesk.domain.pagination;

public record PaginationRequest (

    Integer currentPage,
    Integer pageSize

) {}
