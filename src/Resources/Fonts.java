package Resources;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Fonts {

    public Font dayDreamFont18f;
    public Font dayDreamFont14f;
    public Font dayDreamFont12f;

    public Font minecraftiaFont;

    public Fonts() {
        try {
            dayDreamFont18f = Font.createFont(Font.TRUETYPE_FONT, new File("src/Resources/Fonts/Daydream.ttf"));
            dayDreamFont18f = dayDreamFont18f.deriveFont(18f);

            dayDreamFont14f = Font.createFont(Font.TRUETYPE_FONT, new File("src/Resources/Fonts/Daydream.ttf"));
            dayDreamFont14f = dayDreamFont14f.deriveFont(14f);

            dayDreamFont12f = Font.createFont(Font.TRUETYPE_FONT, new File("src/Resources/Fonts/Daydream.ttf"));
            dayDreamFont12f = dayDreamFont12f.deriveFont(12f);

            minecraftiaFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/Resources/Fonts/Minecraftia.ttf"));
            minecraftiaFont = minecraftiaFont.deriveFont(12f);

        } catch (FontFormatException | IOException exception) {
            throw new RuntimeException("Problem with loading fonts: " + exception);
        }
    }
}
