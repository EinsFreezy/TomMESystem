package de.freezy.mesystem.command;

import de.freezy.mesystem.listener.MeMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.List;

public class MeSystemCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if(strings.length == 0) {
                MeMenu.openMeMenu(player);
            } else if(strings.length == 1) {
                if(strings[0].equalsIgnoreCase("test")) {
                    File file = new File("plugins/MeSystem/Items/" + player.getUniqueId() + ".yml");
                    FileConfiguration cfg = (FileConfiguration) YamlConfiguration.loadConfiguration(file);
                    List<ItemStack> list = (List<ItemStack>) cfg.getList("items");
                    for(ItemStack itemStack : list) {
                        player.sendMessage(itemStack+"");
                    }
                }
            }
        }
        return false;
    }
}
