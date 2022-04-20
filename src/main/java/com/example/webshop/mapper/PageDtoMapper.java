package com.example.webshop.mapper;


import com.example.webshop.dto.PageDto;
import com.example.webshop.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


public interface PageDtoMapper {

    <T> PageDto<T> map(Page<T> page);

}
