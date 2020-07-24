package kz.xan.asiapharm.controllers.image;

import kz.xan.asiapharm.commands.GoodCommand;
import kz.xan.asiapharm.commands.UserCommand;
import kz.xan.asiapharm.converters.GoodToGoodCommand;
import kz.xan.asiapharm.converters.UserToUserCommand;
import kz.xan.asiapharm.domain.Good;
import kz.xan.asiapharm.services.GoodService;
import kz.xan.asiapharm.services.ImageService;
import kz.xan.asiapharm.services.UserService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {
    private final UserService userService;
    private final ImageService imageService;
    private final UserToUserCommand toUserCommand;
    private final GoodService goodService;
    private final GoodToGoodCommand toGoodCommand;

    public ImageController(UserService userService, ImageService imageService, UserToUserCommand toUserCommand, GoodService goodService, GoodToGoodCommand toGoodCommand) {
        this.userService = userService;
        this.imageService = imageService;
        this.toUserCommand = toUserCommand;
        this.goodService = goodService;
        this.toGoodCommand = toGoodCommand;
    }

    @GetMapping("/user/{id}/upload-image")
    public String getUploadUserImage(@PathVariable Long id, Model model){
        model.addAttribute("userCommand", toUserCommand.convert(userService.findById(id)));

        return "user/upload-image";
    }

    @PostMapping("/user/{id}/upload-image")
    public String postUploadUserImage(@PathVariable Long id, @RequestParam("imagefile")MultipartFile file){
        imageService.saveUserImageFile(id, file);

        return "redirect:/user/" + id + "/showInfo";
    }

    @RequestMapping("/user/{id}/image")
    public void getUserImage(@PathVariable Long id, HttpServletResponse response) throws IOException {
        UserCommand userCommand = toUserCommand.convert(userService.findById(id));

        if(userCommand.getImage() != null){
            byte[] byteArray = new byte[userCommand.getImage().length];
            int i = 0;
            for(byte wrappedByte : userCommand.getImage()){
                byteArray[i++] = wrappedByte;
            }
            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }

    @GetMapping("/good/{id}/upload-image")
    public String getUploadGoodImage(@PathVariable Long id, Model model){
        model.addAttribute("goodCommand", toGoodCommand.convert(goodService.findById(id)));

        return "good/upload-image";
    }

    @PostMapping("/good/{id}/upload-image")
    public String postUploadGoodImage(@PathVariable Long id, @RequestParam("imagefile") MultipartFile file){
        imageService.saveGoodImageFile(id, file);

        return "redirect:/good/" + id + "/showInfo";
    }

    @RequestMapping("/good/{id}/image")
    public void getGoodImage(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Good good = goodService.findById(id);
        GoodCommand goodCommand = toGoodCommand.convert(good);

        if(goodCommand.getImage() != null){
            byte[] byteArray = new byte[goodCommand.getImage().length];
            int i = 0;
            for(byte wrappedByte : goodCommand.getImage()){
                byteArray[i++] = wrappedByte;
            }
            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
