package com.example.webshop.mapper.impl;

import com.example.webshop.dto.PageDto;
import com.example.webshop.mapper.PageDtoMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageDtoMapperImpl implements PageDtoMapper {

    @Override
    public <T> PageDto<T> map(Page<T> page) {
        return new PageDto<T>(page);
    }

}
