package it.schwarz.bogy.ld2i.domain.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
            ig2.setBackground(Color.BLUE);
            ig2.fillRect(0,0, width, height);

            // Text
            Font font = new Font("TimesRoman", Font.BOLD, 20);
            ig2.setFont(font);
            String message = labelData.articleText() + " - " + labelData.getCanonicalPrice();
            FontMetrics fontMetrics = ig2.getFontMetrics();
            int stringWidth = fontMetrics.stringWidth(message);
            int stringHeight = fontMetrics.getAscent();
            ig2.setPaint(Color.black);
            ig2.drawString(message, (width - stringWidth) / 2, height / 2 + stringHeight / 4);

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
