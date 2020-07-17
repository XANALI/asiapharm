package kz.xan.asiapharm.services.impl;

import kz.xan.asiapharm.domain.User;
import kz.xan.asiapharm.repositories.UserRepository;
import kz.xan.asiapharm.services.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private final UserRepository userRepository;

    public ImageServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
