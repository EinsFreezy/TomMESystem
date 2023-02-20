package de.freezy.teleport;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LocMobCMDInterface implements TabCompleter {

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        final List<String> match = new ArrayList<>();

            //get all entity types
            EntityType[] entityTypes = EntityType.values();
            for (EntityType entityType : entityTypes) {
                //check if the entity type is a mob
                if (entityType.isAlive()) {
                    //add the mob to the tab completion
                    match.add(entityType.name());
                }
            }


        return (List<String>) match;

    }


}
