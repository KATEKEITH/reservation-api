package kr.or.connect.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.dao.DisplayinfoDao;
import kr.or.connect.dto.Displayinfo;

@Service
public class DisplayInfoServiceImpl implements DisplayInfoServie {

    private final DisplayinfoDao displayInfoDao;

    public DisplayInfoServiceImpl(DisplayinfoDao displayInfoDao) {
        this.displayInfoDao = displayInfoDao;
    }

    @Override
    public List<Displayinfo> getProducts(Integer categoryId, Integer start) {
        List<Displayinfo> list = displayInfoDao.selectAll(categoryId, start, DisplayInfoServie.LIMIT);
        return list;
    }

    @Override
    public Displayinfo getProductsById(Integer displayId) {
        return displayInfoDao.selectProductById(displayId);
    }

    @Override
    public int getProductCount(Integer categoryId) {
        return displayInfoDao.selectCount(categoryId);
    }

}
