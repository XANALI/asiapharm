package kz.xan.asiapharm.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void saveUserImageFile(Long userId, MultipartFile file);
    void saveGoodImageFile(Long goodId, MultipartFile file);
}
