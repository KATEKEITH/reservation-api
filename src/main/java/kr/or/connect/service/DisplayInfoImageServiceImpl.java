package kr.or.connect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.dao.DisplayInfoImageDao;
import kr.or.connect.dto.DisplayInfoImage;

@Service
public class DisplayInfoImageServiceImpl implements DisplayInfoImageService {

    private final DisplayInfoImageDao displayInfoImageDao;

    public DisplayInfoImageServiceImpl(DisplayInfoImageDao displayInfoImageDao) {
        this.displayInfoImageDao = displayInfoImageDao;
    }

    @Override
    public List<DisplayInfoImage> getDisplayInfoImages(Integer displayId) {
        List<DisplayInfoImage> list = displayInfoImageDao.selectById(displayId);
        return list;
    }

}
