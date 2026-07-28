package com.project.helpdesk.domain.pagination;

import java.util.List;

public record PaginatedResult<T> (

    List<T> data,
    Integer currentPage,
    Integer pageSize,
    Long totalElements,
    Integer totalPages

) {}
