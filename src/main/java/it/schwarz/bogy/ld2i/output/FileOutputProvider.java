package it.schwarz.bogy.ld2i.output;

import it.schwarz.bogy.ld2i.domain.usecase.OutputProvider;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class FileOutputProvider implements OutputProvider {

    private final File targetDirectory;

    public FileOutputProvider(File targetDirectory) {
        this.targetDirectory = targetDirectory;
    }

    @Override
    public void send(String displayId, InputStream image) {
        OutputStream outStream = null;
        try {
            byte[] buffer = image.readAllBytes();
            outStream = new FileOutputStream(new File(targetDirectory, displayId + ".PNG"));
            outStream.write(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(outStream);
        }
    }
}
