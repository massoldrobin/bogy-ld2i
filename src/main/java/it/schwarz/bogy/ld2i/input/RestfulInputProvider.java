package it.schwarz.bogy.ld2i.input;

import it.schwarz.bogy.ld2i.domain.entity.Display;
import it.schwarz.bogy.ld2i.domain.entity.LabelData;
import it.schwarz.bogy.ld2i.domain.usecase.CreateLabelUseCase;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class RestfulInputProvider {

    private final CreateLabelUseCase createLabelUseCase;

    public RestfulInputProvider(CreateLabelUseCase createLabelUseCase) {
        this.createLabelUseCase = createLabelUseCase;
    }

    @GetMapping(
            path = "/label",
            produces = MediaType.IMAGE_PNG_VALUE)
    byte[] createLabel(
            @RequestParam String displayId,
            @RequestParam String articleText,
            @RequestParam BigDecimal articlePrice,
            @RequestParam String adText,
            @RequestParam BigDecimal oldArticlePrice,
            @RequestParam String currency,
            @RequestParam String attentionText,
            @RequestParam String nowText) {

        Display display = Display.byId(displayId);

        LabelData labelData = new LabelData(
                display.getDisplayId(),
                display.getWidth(),
                display.getHeight(),
                articleText,
                articlePrice,
                adText,
                oldArticlePrice,
                currency,
                attentionText,
                nowText);
        return createLabelUseCase.createLabelAndReturn(labelData);
    }
}
