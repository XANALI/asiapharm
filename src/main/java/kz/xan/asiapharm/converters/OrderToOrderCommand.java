package kz.xan.asiapharm.converters;

import com.sun.istack.Nullable;
import kz.xan.asiapharm.commands.OrderCommand;
import kz.xan.asiapharm.domain.Order;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderToOrderCommand implements Converter<Order, OrderCommand> {

    private final UserToUserCommand userToUserCommand;
    private final GoodToGoodCommand goodToGoodCommand;

    public OrderToOrderCommand(UserToUserCommand userToUserCommand, GoodToGoodCommand goodToGoodCommand) {
        this.userToUserCommand = userToUserCommand;
        this.goodToGoodCommand = goodToGoodCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public OrderCommand convert(Order order) {
        if(order == null){
            return null;
        }

        final OrderCommand orderCommand = new OrderCommand();
        orderCommand.setDescription(order.getDescription());
        orderCommand.setUser(userToUserCommand.convert(order.getUser()));
        orderCommand.setGood(goodToGoodCommand.convert(order.getGood()));

        return orderCommand;
    }
}
