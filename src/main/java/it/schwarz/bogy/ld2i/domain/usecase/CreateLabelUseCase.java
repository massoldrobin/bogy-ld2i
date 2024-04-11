package it.schwarz.bogy.ld2i.domain.usecase;

import it.schwarz.bogy.ld2i.domain.entity.LabelCreator;
import it.schwarz.bogy.ld2i.domain.entity.LabelData;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class CreateLabelUseCase {

    public CreateLabelUseCase(LabelDataProvider labelDataProvider, OutputProvider outputProvider) {
        this.labelDataProvider = labelDataProvider;
        this.outputProvider = outputProvider;
    }

    private final LabelDataProvider labelDataProvider;
    private final OutputProvider outputProvider;

    private final LabelCreator labelCreator = new LabelCreator();


    public void createLabelAndOutput() {
        // Get Data
        LabelData labelData = labelDataProvider.getLabelData();

        // Create Image
        byte[] image = this.createLabelAndReturn(labelData);

        // Output Image
        InputStream is = new ByteArrayInputStream(image);
        outputProvider.send(labelData.displayId(), is);
        IOUtils.closeQuietly(is);
    }

    public byte[] createLabelAndReturn(LabelData labelData) {
        return labelCreator.createLabel(labelData);
    }
}
