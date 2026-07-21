package com.project.helpdesk.domain.pagination;

import java.util.List;

public record PaginationResult<T> (

    List<T> data,
    Integer currentPage,
    Integer pageSize,
    Long totalElements,
    Integer totalPages

) {}
