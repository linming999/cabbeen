package com.kabin.goods.domain.dto;

import lombok.Data;
import java.util.List;

@Data
public class GoodsDto {
    private Long id;
    private String name;
    private Double price;
    private List<String> imageList;
}
