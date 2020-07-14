package kz.xan.asiapharm.converters;

import com.sun.istack.Nullable;
import kz.xan.asiapharm.commands.UserCommand;
import kz.xan.asiapharm.domain.User;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserCommand implements Converter<User, UserCommand> {

    @Synchronized
    @Nullable
    @Override
    public UserCommand convert(User user) {
        if(user == null) {
            return null;
        }

        final UserCommand userCommand = new UserCommand();
        userCommand.setId(user.getId());
        userCommand.setFirstName(user.getFirstName());
        userCommand.setLastName(user.getLastName());
        userCommand.setUsername(user.getUsername());
        userCommand.setPassword(user.getPassword());
        userCommand.setMobileNum(user.getMobileNum());
        userCommand.setEmail(user.getEmail());
        userCommand.setBirthDate(user.getBirthDate());
        userCommand.setRole(user.getRole());

        return userCommand;
    }
}
