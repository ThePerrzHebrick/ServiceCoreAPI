package net.craftergames.projects.colors.manager;

import net.craftergames.projects.colors.ChatColor;
import net.craftergames.projects.colors.Rainbow;
import java.util.ArrayList;

public class HexCodeManager {

    public static String createGradientFromString(String message, String[] colours) {

        int count = message.length();
        if (Math.min(count, colours.length) < 2) {
            return message;
        }

        ArrayList<String> cols = createGradient(count, colours);

        String colourCodes = "";
        for (int i = 0; i < cols.size(); i++) {
            colourCodes += ChatColor.of(cols.get(i)) + "" + message.charAt(i);
        }
        return colourCodes;
    }

    public static ArrayList<String> createGradient(int count, String[] colours) {
        Rainbow rainbow = new Rainbow();

        try {
            rainbow.setNumberRange(1, count);
            rainbow.setSpectrum(colours);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<String> hexCodes = new ArrayList<String>();
        for (int i = 1; i <= count; i++) {
            hexCodes.add("#" + rainbow.colourAt(i));
        }
        return hexCodes;
    }
}
