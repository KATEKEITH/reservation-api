package kr.or.connect.service;

import java.util.List;

import kr.or.connect.dto.Displayinfo;

public interface DisplayInfoServie {

    //
    int LIMIT = 4;

    public List<Displayinfo> getProducts(Integer categoryId, Integer start);

    public Displayinfo getProductsById(Integer displayId);

    public int getProductCount(Integer categoryId);

}
