package it.schwarz.bogy.ld2i.domain.entity;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BarcodeGenerator {
    private static final Font BARCODE_TEXT_FONT = Font.decode("ITALIC") ;

    public static BufferedImage generateEAN13BarcodeImage(String barcodeText) throws Exception {
        Barcode barcode = BarcodeFactory.createEAN13(barcodeText);
        barcode.setFont(BARCODE_TEXT_FONT);

        return BarcodeImageHandler.getImage(barcode);

    }
}


