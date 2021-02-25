package kr.or.connect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.dao.PromotionDao;
import kr.or.connect.dto.Promotion;

@Service
public class PromotionServiceImpl implements PromotionService {

    private final PromotionDao promotionDao;

    public PromotionServiceImpl(PromotionDao promotionDao) {
        this.promotionDao = promotionDao;
    }

    public List<Promotion> getPromotions() {
        List<Promotion> list = promotionDao.selectAll();
        return list;
    }

    @Override
    public int getPromotionCount() {
        return promotionDao.selectCount();
    }

}
