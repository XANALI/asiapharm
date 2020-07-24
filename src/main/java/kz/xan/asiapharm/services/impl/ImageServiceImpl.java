package kz.xan.asiapharm.services.impl;

import kz.xan.asiapharm.domain.Good;
import kz.xan.asiapharm.domain.User;
import kz.xan.asiapharm.repositories.GoodRepository;
import kz.xan.asiapharm.repositories.UserRepository;
import kz.xan.asiapharm.services.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private final UserRepository userRepository;
    private final GoodRepository goodRepository;

    public ImageServiceImpl(UserRepository userRepository, GoodRepository goodRepository) {
        this.userRepository = userRepository;
        this.goodRepository = goodRepository;
    }

    @Override
    @Transactional
    public void saveUserImageFile(Long userId, MultipartFile file) {
        try{
            User user = userRepository.findById(userId).get();
            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for(byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            user.setImage(byteObjects);
            userRepository.save(user);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void saveGoodImageFile(Long goodId, MultipartFile file) {
        try{
            Good good = goodRepository.findById(goodId).orElse(null);
            Byte[] byteObjects = new Byte[file.getBytes().length];
            int i = 0;

            for(byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            assert good != null;
            good.setImage(byteObjects);
            goodRepository.save(good);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
