package it.schwarz.bogy.ld2i.domain.usecase;

import java.io.InputStream;

public interface OutputProvider {
    void send(String displayId, InputStream image);
}
