package com.ftn.sbnz.service.Entities.Tools;


import lombok.Data;

import java.beans.ConstructorProperties;

@Data

public class PageParams {
    private Integer page;
    private Integer pageSize;
    private String search;

    @ConstructorProperties({"page", "pageSize", "search"})
    public PageParams(Integer page, Integer pageSize, String search) {
        this.page = page != null ? page : Integer.valueOf("1");
        this.pageSize = pageSize != null ? pageSize : Integer.valueOf("5");
        this.search = search != null ? search : "";
    }

}