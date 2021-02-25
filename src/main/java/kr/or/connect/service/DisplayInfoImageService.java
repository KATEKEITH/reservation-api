package kr.or.connect.service;

import java.util.List;

import kr.or.connect.dto.DisplayInfoImage;

public interface DisplayInfoImageService {

    public List<DisplayInfoImage> getDisplayInfoImages(Integer displayId);

}
