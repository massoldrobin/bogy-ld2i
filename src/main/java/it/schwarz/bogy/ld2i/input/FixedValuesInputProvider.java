package it.schwarz.bogy.ld2i.input;

import it.schwarz.bogy.ld2i.domain.entity.Display;
import it.schwarz.bogy.ld2i.domain.entity.LabelData;
import it.schwarz.bogy.ld2i.domain.usecase.LabelDataProvider;

import java.math.BigDecimal;

public class FixedValuesInputProvider implements LabelDataProvider {
    @Override
    public LabelData getLabelData() {
        Display d = Display.DISPLAY_0AC0F23A70D8_16;
        return new LabelData(
                d.getDisplayId(),
                d.getWidth(),
                d.getHeight(),
                "Apples for only",
                new BigDecimal("1.99"),
                "INSTEAD OF",
                new BigDecimal("4.99"),
                "$",
                "BIG SALE!!!",
                "NOW,"
        );
    }
}
