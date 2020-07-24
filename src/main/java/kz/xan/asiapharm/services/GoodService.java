package kz.xan.asiapharm.services;

import kz.xan.asiapharm.commands.GoodCommand;
import kz.xan.asiapharm.domain.Good;

import java.util.Set;

public interface GoodService extends CrudService<Good, Long> {
    Set<GoodCommand> findAllCommands();
    GoodCommand findCommandByID(Long id);
}
