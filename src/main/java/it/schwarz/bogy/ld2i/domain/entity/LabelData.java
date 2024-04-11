package it.schwarz.bogy.ld2i.domain.entity;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public record LabelData(
        String displayId,
        int displayWidth,
        int displayHeight,
        String articleText,
        BigDecimal price
) {
    public String getCanonicalPrice() {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(0);
        df.setGroupingUsed(false);
        return df.format(price);
    }
}
