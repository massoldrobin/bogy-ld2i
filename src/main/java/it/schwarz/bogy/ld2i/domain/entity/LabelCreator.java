package it.schwarz.bogy.ld2i.domain.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.awt.geom.Rectangle2D;
import java.util.Map;

public class LabelCreator {

    private final Logger logger = LoggerFactory.getLogger(LabelCreator.class);

    public byte[] createLabel(LabelData labelData) {
        logger.info("Creating image ...");
        try {
            int width = labelData.displayWidth();
            int height = labelData.displayHeight();

            // TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed
            // into integer pixels
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            Graphics2D ig2 = bi.createGraphics();

            // Background
            ig2.setColor(Color.RED);
            ig2.fillRect(0,0, width, height);

            // Text
            Font font = new Font("Arial", Font.BOLD, 20);
            String message = labelData.articleText() + " - " + labelData.getCanonicalPrice();
            String adMessage = labelData.adText()+ " - ";
            String oldText = labelData.getCanonicalOldPrice();
            ig2.setFont(font);

            FontMetrics fontMetrics = ig2.getFontMetrics();

            int stringWidth = fontMetrics.stringWidth(adMessage);
            int stringWidth2 =fontMetrics.stringWidth(message);
            int stringHeight = fontMetrics.getAscent();

            ig2.setPaint(Color.YELLOW);
            ig2.drawString(message,(width - stringWidth2) / 2, height / 2 + stringHeight / 1);
            ig2.drawString(adMessage, (width - stringWidth) /6, height / 5  + stringHeight / 4);


            Map attributes = font.getAttributes();
            attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
            Font newFont = new Font(attributes);
            ig2.setFont(newFont);

            ig2.drawString(oldText, (width - stringWidth) + 140, height / 5  + stringHeight / 4);

            // Picture (e.g. Cheaper)


            // Line


            // Barcode

            // Convert to ByteArray and return
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bi, "PNG", os);
            logger.info("Creating image ... done");
            return os.toByteArray();

        } catch (IOException ie) {
            throw new RuntimeException(ie);
        }

    }
}
