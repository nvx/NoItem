package net.worldoftomorrow.nala.ni;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class StringHelper {
	public static String replaceVars(String msg, Player p, int id) {
		String x = Integer.toString(p.getLocation().getBlockX());
		String y = Integer.toString(p.getLocation().getBlockY());
		String z = Integer.toString(p.getLocation().getBlockZ());
		msg = msg.replace("%n", p.getDisplayName());
		msg = msg.replace("%w", p.getWorld().getName());
		msg = msg.replace("%x", x);
		msg = msg.replace("%y", y);
		msg = msg.replace("%z", z);
		if (Material.getMaterial(id) != null) {
			Material mat = Material.getMaterial(id);
			String name = mat.name().toLowerCase().replace("_", " ");
			msg = msg.replace("%i", name);
		} else {
			String sid = Integer.toString(id);
			msg = msg.replace("%i", sid);
		}
		msg = StringHelper.parseColors(msg);
		return msg;
	}

	public static String replaceVars(String msg, EventTypes type, Player p,
			String recipe) {
		String x = Integer.toString(p.getLocation().getBlockX());
		String y = Integer.toString(p.getLocation().getBlockY());
		String z = Integer.toString(p.getLocation().getBlockZ());
		msg = msg.replace("%n", p.getDisplayName());
		msg = msg.replace("%w", p.getWorld().getName());
		msg = msg.replace("%x", x);
		msg = msg.replace("%y", y);
		msg = msg.replace("%z", z);
		msg = msg.replace("%i", recipe);
		msg = msg.replace("%t", type.getName());
		msg = StringHelper.parseColors(msg);
		return msg;
	}

	public static String replaceVars(String msg, Player p, EventTypes type,
			ItemStack stack) {
		return replaceVars(msg, p, type, stack.getTypeId());
	}

	public static String replaceVars(String msg, Player p, EventTypes type, int id) {
		String x = Integer.toString(p.getLocation().getBlockX());
		String y = Integer.toString(p.getLocation().getBlockY());
		String z = Integer.toString(p.getLocation().getBlockZ());
		Material mat = Material.getMaterial(id);
		if (mat != null) {
			String name = mat.name().toLowerCase().replace("_", " ");
			msg = msg.replace("%i", name);
		} else {
			String sid = Integer.toString(id);
			msg = msg.replace("%i", sid);
		}

		msg = msg.replace("%n", p.getDisplayName());
		msg = msg.replace("%w", p.getWorld().getName());
		msg = msg.replace("%x", x);
		msg = msg.replace("%y", y);
		msg = msg.replace("%z", z);
		msg = msg.replace("%t", type.getName());
		msg = StringHelper.parseColors(msg);
		return msg;
	}

	public static String replaceVars(String msg, Player p, EventTypes type,
			String recipe) {
		String x = Integer.toString(p.getLocation().getBlockX());
		String y = Integer.toString(p.getLocation().getBlockY());
		String z = Integer.toString(p.getLocation().getBlockZ());

		msg = msg.replace("%i", recipe);
		msg = msg.replace("%n", p.getDisplayName());
		msg = msg.replace("%w", p.getWorld().getName());
		msg = msg.replace("%x", x);
		msg = msg.replace("%y", y);
		msg = msg.replace("%z", z);
		msg = msg.replace("%t", type.getName());
		msg = StringHelper.parseColors(msg);
		return msg;
	}

	public static void notifyPlayer(Player p, EventTypes type, String recipe) {
		String msg = type.getMessage();
		p.sendMessage(ChatColor.BLUE + replaceVars(msg, type, p, recipe));
	}

	public static void notifyPlayer(Player p, EventTypes type, ItemStack stack) {
		if (type.doNotify()) {
			String msg = type.getMessage();
			p.sendMessage(ChatColor.BLUE + replaceVars(msg, p, stack.getTypeId()));
		}
	}

	public static void notifyAdmin(Player p, EventTypes type, String recipe) {
		if (Config.getBoolean("Notify.Admin")) {
			String message = StringHelper.replaceVars(Config.getString("Messages.Admin"), p,
					type, recipe);
			Player[] players = Bukkit.getOnlinePlayers();
			for (Player player : players) {
				if (Perms.ADMIN.has(player)) {
					player.sendMessage(ChatColor.RED + "[NI] " + ChatColor.BLUE
							+ message);
				}
			}
		}
	}

	public static void notifyAdmin(Player p, EventTypes type, ItemStack stack) {
		if (Config.getBoolean("Notify.Admin")) {
			String message = StringHelper.replaceVars(Config.getString("Messages.Admin"), p,
					type, stack);
			// log.log(message);
			Player[] players = Bukkit.getOnlinePlayers();
			for (Player player : players) {
				if (Perms.ADMIN.has(player)) {
					player.sendMessage(ChatColor.RED + "[NI] " + ChatColor.BLUE
							+ message);
				}
			}
		}
	}

	public static void notifyAdmin(Player p, Block b) {
		if (Config.getBoolean("Notify.Admin")) {
			String message = StringHelper.replaceVars(Config.getString("Messages.Admin"), p,
					EventTypes.BREAK, b.getTypeId());
			// log.log(message);
			Player[] players = Bukkit.getOnlinePlayers();
			for (Player player : players) {
				if (Perms.ADMIN.has(player)) {
					player.sendMessage(ChatColor.RED + "[NI] " + ChatColor.BLUE
							+ message);
				}
			}
		}
	}

	public static String parseColors(String msg) {
		msg = msg.replace("&0", ChatColor.BLACK.toString());
		msg = msg.replace("&1", ChatColor.DARK_BLUE.toString());
		msg = msg.replace("&2", ChatColor.DARK_GREEN.toString());
		msg = msg.replace("&3", ChatColor.DARK_AQUA.toString());
		msg = msg.replace("&4", ChatColor.DARK_RED.toString());
		msg = msg.replace("&5", ChatColor.DARK_PURPLE.toString());
		msg = msg.replace("&6", ChatColor.GOLD.toString());
		msg = msg.replace("&7", ChatColor.GRAY.toString());
		msg = msg.replace("&8", ChatColor.DARK_GRAY.toString());
		msg = msg.replace("&9", ChatColor.BLUE.toString());
		msg = msg.replace("&a", ChatColor.GREEN.toString());
		msg = msg.replace("&b", ChatColor.AQUA.toString());
		msg = msg.replace("&c", ChatColor.RED.toString());
		msg = msg.replace("&d", ChatColor.LIGHT_PURPLE.toString());
		msg = msg.replace("&e", ChatColor.YELLOW.toString());
		msg = msg.replace("&f", ChatColor.WHITE.toString());
		msg = msg.replace("&k", ChatColor.MAGIC.toString());
		msg = msg.replace("&l", ChatColor.BOLD.toString());
		msg = msg.replace("&m", ChatColor.STRIKETHROUGH.toString());
		msg = msg.replace("&n", ChatColor.UNDERLINE.toString());
		msg = msg.replace("&o", ChatColor.ITALIC.toString());
		msg = msg.replace("&r", ChatColor.RESET.toString());
		return msg;
	}
	// TODO: separate option for log to console.
}