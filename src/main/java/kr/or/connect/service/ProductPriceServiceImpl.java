package kr.or.connect.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.dao.ProductPriceDao;
import kr.or.connect.dto.Price;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {

    private final ProductPriceDao productPriceDao;

    public ProductPriceServiceImpl(ProductPriceDao productPriceDao) {
        this.productPriceDao = productPriceDao;
    }

    @Override
    public List<Price> getProductPrices(Integer displayId) {
        List<Price> list = productPriceDao.selectById(displayId);
        return list;
    }

}
