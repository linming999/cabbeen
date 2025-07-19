package com.kabin.goods.domain.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GoodsDetailDto {
    private Long id;
    private String name;
    private Double price;
    private String description;

    @Data
    public static class StyleDto {
        private String style;
        private String cover;
        private List<String> images;
    }

    private List<StyleDto> styles;
}
