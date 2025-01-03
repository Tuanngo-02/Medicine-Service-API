package com.service.medicine.controller;

import com.service.medicine.dto.response.PageResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.service.medicine.dto.request.ProductRequest;
import com.service.medicine.dto.response.ApiResponse;
import com.service.medicine.dto.response.ProductResponse;
import com.service.medicine.service.impl.ProductServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductServiceImpl medicineService;
    private final String DEFAULT_PAGE_NUMBER = "1";
    private final String DEFAULT_PAGE_SIZE = "2";
    private final String DEFAULT_SORT_BY = "price";

    @Operation(summary = "Add new product", description = "Send a request via this API to add new product")
    @PostMapping
    ApiResponse<ProductResponse> createMedicine(@RequestBody ProductRequest request) throws Exception {
        return ApiResponse.<ProductResponse>builder()
                .result(medicineService.createMedicine(request))
                .build();
    }

    @PostMapping(value = "/image/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload an image into product by ID", description = "Insert an image into a product by ID product")
    public ApiResponse<ProductResponse> uploadImage(@PathVariable Long id, @RequestPart("file") MultipartFile file) throws Exception {
        return ApiResponse.<ProductResponse>builder()
                .result(medicineService.uploadImage(id, file))
                .build();
    }



    @Operation(
            summary = "Get list of products per page, search by name product or category",
            description = "Send a request via this API to get product list by page and pageSize, search product by name or category")
    @GetMapping
    ApiResponse<PageResponse<ProductResponse>> getAllMedicine(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(defaultValue = DEFAULT_SORT_BY) String sortBy) {
        return ApiResponse.<PageResponse<ProductResponse>>builder()
                .result(medicineService.getAllMedicine(keyword, category,page, pageSize, sortBy))
                .build();
    }
    @Operation(summary = "Update product", description = "Send a request via this API to update product")
    @PutMapping("/{id}")
    ApiResponse<ProductResponse> updateMedicine(@PathVariable Long id, @RequestBody ProductRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .result(medicineService.updateMedicine(id, request))
                .build();
    }

    @Operation(summary = "Delete product", description = "Send a request via this API to delete product")
    @DeleteMapping("/{id}")
    ApiResponse<Void> deleteMedicine(@PathVariable Long id) {
        medicineService.deleteMedicine(id);
        return ApiResponse.<Void>builder().build();
    }
}
