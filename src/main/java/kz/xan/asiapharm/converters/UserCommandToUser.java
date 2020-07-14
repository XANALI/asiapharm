package kz.xan.asiapharm.converters;

import com.sun.istack.Nullable;
import kz.xan.asiapharm.commands.UserCommand;
import kz.xan.asiapharm.domain.User;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserCommandToUser implements Converter<UserCommand, User> {

    @Synchronized
    @Nullable
    @Override
    public User convert(UserCommand userCommand) {
        if(userCommand == null){
            return null;
        }
        final User user = new User();
        user.setId(userCommand.getId());
        user.setFirstName(userCommand.getFirstName());
        user.setLastName(userCommand.getLastName());
        user.setUsername(userCommand.getUsername());
        user.setPassword(userCommand.getPassword());
        user.setMobileNum(userCommand.getMobileNum());
        user.setEmail(userCommand.getEmail());
        user.setBirthDate(userCommand.getBirthDate());
        user.setRole(userCommand.getRole());

        return user;
    }
}
