package kr.or.connect.service;

import java.util.List;

import kr.or.connect.dto.Price;

public interface ProductPriceService {

    public List<Price> getProductPrices(Integer displayId);

}
