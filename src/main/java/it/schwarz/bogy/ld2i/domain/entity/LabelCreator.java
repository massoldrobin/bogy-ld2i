package it.schwarz.bogy.ld2i.domain.entity;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.File;




public class LabelCreator {

    private final Logger logger = LoggerFactory.getLogger(LabelCreator.class);

    public byte[] createLabel(LabelData labelData) {
        logger.info("Creating image ...");
        try {
            // uploading picture
            File imageFile = new File("C:\\Users\\massoldr\\IdeaProjects\\bogy-ld2i\\src\\main\\resources\\kaufland.png");
            BufferedImage bufferedImage = ImageIO.read(imageFile);

            //generatíng barcode


            int width = labelData.displayWidth();
            int height = labelData.displayHeight();

            // TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed
            // into integer pixels
            BufferedImage bi = new BufferedImage(width , height, BufferedImage.TYPE_INT_ARGB);

            Graphics2D ig2 = bi.createGraphics();


            // Text
            String message = labelData.articleText() + " - " + labelData.getCanonicalPrice();
            String adMessage = labelData.adText()+ " - ";
            String oldPrice = labelData.getCanonicalOldPrice();
            String currency = labelData.currency();
            String attention = labelData.attentionText();
            String attentionNow = labelData.nowText();


            FontMetrics fontMetrics = ig2.getFontMetrics();

            float stringWidthAdMessage = fontMetrics.stringWidth(adMessage);
            float stringWidthMessage =fontMetrics.stringWidth(message);
            float stringWidthAttention =fontMetrics.stringWidth(attention);
            float stringWithOldPrice =fontMetrics.stringWidth(oldPrice);
            float stringWithNOW = fontMetrics.stringWidth(attentionNow);

            // Background
            ig2.setColor(Color.WHITE);
            ig2.fillRect(0,0, width, height);

            ig2.setColor(Color.RED);
            ig2.fillRect(5,5, width -10, height -10);

            ig2.setColor(Color.WHITE);
            ig2.fillRect(15,15, width- 30, height- 30);



            if(width == 200 && height == 200) {
                Font fontAdSmall = new Font("Arial", Font.BOLD, 25);
                Font fontSmall = new Font("Arial", Font.BOLD, 13);
                Font fontNOWSmall = new Font("Arial", Font.BOLD, 23);

                ig2.setPaint(Color.RED);
                ig2.setFont(fontAdSmall);
                ig2.drawString(attention, (width - stringWidthAttention) / 4 , height / 10 * 2);

                ig2.setFont(fontNOWSmall);
                ig2.drawString(attentionNow, (width - stringWithNOW) / 2 ,height / 20 * 11 );

                ig2.setFont(fontSmall);
                ig2.drawString(adMessage, (width - stringWidthAdMessage) /3, height / 20 * 7);
                ig2.drawString(oldPrice, (width - stringWithOldPrice) / 20 * 15, height / 20 * 7);
                ig2.drawString(message,(width - stringWidthMessage) / 3, height / 20 * 15 );
                ig2.drawString(currency, (width- stringWithOldPrice)  / 10 * 9, height / 20 * 7);
                ig2.drawString(currency, (width- stringWithOldPrice)  / 40 * 37 , height / 20 * 15);

                // creating Strike trough old Price
                ig2.setColor(Color.RED);
                ig2.fillRect(130,65, 35,2);

                //picture
                ig2.drawImage(bufferedImage, 20, 77,50,50,null);

                // Barcode
                ig2.drawImage(BarcodeGenerator.generateEAN13BarcodeImage("400763000011"),25, 160, 150, 25,null);

            }
            else {
                Font fontAd = new Font("Arial", Font.BOLD, 40);
                Font font = new Font("Arial", Font.BOLD, 20);
                Font fontNOW = new Font("Arial", Font.BOLD, 30);
                // creating Text at Display
                ig2.setPaint(Color.RED);
                ig2.setFont(fontAd);
                ig2.drawString(attention, (width - stringWidthAttention) / 4, height / 10 * 3);

                ig2.setFont(fontNOW);
                ig2.drawString(attentionNow, (width - stringWithNOW) / 8, height / 40 * 33);

                ig2.setFont(font);
                ig2.drawString(adMessage, (width - stringWidthAdMessage) / 3, height / 2);
                ig2.drawString(oldPrice, (width - stringWithOldPrice) / 40 * 27, height / 2);
                ig2.drawString(message, (width - stringWidthMessage) / 2, height / 20 * 14);
                ig2.drawString(currency, (width - stringWithOldPrice) / 10 * 8, height / 2);
                ig2.drawString(currency, (width - stringWithOldPrice) / 20 * 19, height / 20 * 14);

                // creating Strike trough old Price
                ig2.setColor(Color.RED);
                ig2.fillRect(238, 83, 55, 2);

                //picture
                ig2.drawImage(bufferedImage, 20, 20, 50, 50, null);

                // Barcode
                ig2.drawImage(BarcodeGenerator.generateEAN13BarcodeImage("400763000011"), 209, 133, 160, 30, null);
            }

            // Line


            // Convert to ByteArray and return
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bi, "PNG", os);
            logger.info("Creating image ... done");
            return os.toByteArray();

        } catch (IOException ie) {
            throw new RuntimeException(ie);
        } catch (Exception e) {
            logger.error("Error creating image ... done");
            throw new RuntimeException(e);
        }

    }
}
