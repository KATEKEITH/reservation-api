package kr.or.connect.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.dao.ProductImageDao;
import kr.or.connect.dto.ProductImage;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductImageDao productImageDao;

    public ProductImageServiceImpl(ProductImageDao productImageDao) {
        this.productImageDao = productImageDao;
    }

    @Override
    public List<ProductImage> getProductImages(Integer displayId) {
        List<ProductImage> list = productImageDao.selectById(displayId);
        return list;
    }

}
