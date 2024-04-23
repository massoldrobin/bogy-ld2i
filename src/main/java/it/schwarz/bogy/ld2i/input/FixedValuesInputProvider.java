package it.schwarz.bogy.ld2i.input;

import it.schwarz.bogy.ld2i.domain.entity.Display;
import it.schwarz.bogy.ld2i.domain.entity.LabelData;
import it.schwarz.bogy.ld2i.domain.usecase.LabelDataProvider;

import java.math.BigDecimal;

public class FixedValuesInputProvider implements LabelDataProvider {
    @Override
    public LabelData getLabelData() {
        Display d = Display.DISPLAY_0ABE5B6D74D4_35;
        return new LabelData(
                d.getDisplayId(),
                d.getWidth(),
                d.getHeight(),
                "Apples",
                new BigDecimal("1.99"),
                "Big Sale",
                new BigDecimal("4.99"),
                "$"
        );
    }
}
