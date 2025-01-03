package com.service.medicine.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.service.medicine.dto.request.ProductRequest;
import com.service.medicine.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    // thay thế bằng class subProductMapper

    @Mapping(target = "category", ignore = true)
    void updateMedicine(@MappingTarget Product product, ProductRequest request);
}
