package kz.xan.asiapharm.converters;

import com.sun.istack.Nullable;
import kz.xan.asiapharm.commands.OrderCommand;
import kz.xan.asiapharm.domain.Order;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderCommandToOrder implements Converter<OrderCommand, Order> {

    private final UserCommandToUser userCommandToUser;
    private final GoodCommandToGood goodCommandToGood;

    public OrderCommandToOrder(UserCommandToUser userCommandToUser, GoodCommandToGood goodCommandToGood) {
        this.userCommandToUser = userCommandToUser;
        this.goodCommandToGood = goodCommandToGood;
    }

    @Synchronized
    @Nullable
    @Override
    public Order convert(OrderCommand orderCommand) {
        if(orderCommand == null){
            return null;
        }

        final Order order = new Order();
        order.setId(orderCommand.getId());
        order.setDescription(order.getDescription());
        order.setUser(userCommandToUser.convert(orderCommand.getUser()));
        order.setGood(goodCommandToGood.convert(orderCommand.getGood()));

        return order;
    }
}
