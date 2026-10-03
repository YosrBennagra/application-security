package example.security;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

public final class SafeFileStorage {
    private static final int BUFFER_SIZE = 8192;
    private final Path root;

    public SafeFileStorage(Path root) {
        this.root = root.toAbsolutePath().normalize();
    }

    public Path store(InputStream input, long declaredSize, long maxSize) throws IOException {
        if (declaredSize < 0 || declaredSize > maxSize || maxSize <= 0) {
            throw new IllegalArgumentException("File size rejected");
        }

        Files.createDirectories(root);
        Path destination = root.resolve(UUID.randomUUID().toString() + ".bin").normalize();

        if (!destination.startsWith(root)) {
            throw new SecurityException("Invalid storage path");
        }

        long copied = 0;
        byte[] buffer = new byte[BUFFER_SIZE];

        try (OutputStream output = Files.newOutputStream(destination, StandardOpenOption.CREATE_NEW)) {
            for (int read; (read = input.read(buffer)) != -1; ) {
                copied += read;
                if (copied > maxSize) {
                    throw new IllegalArgumentException("Actual file size exceeds limit");
                }
                output.write(buffer, 0, read);
            }
        } catch (RuntimeException | IOException failure) {
            Files.deleteIfExists(destination);
            throw failure;
        }

        return destination;
    }
}
