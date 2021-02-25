package kr.or.connect.service;

import java.util.List;

import kr.or.connect.dto.Promotion;

public interface PromotionService {

    //
    public List<Promotion> getPromotions();

    int getPromotionCount();

}
