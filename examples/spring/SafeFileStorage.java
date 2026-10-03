package example.security;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public final class SafeFileStorage {
    private final Path root;

    public SafeFileStorage(Path root) {
        this.root = root.toAbsolutePath().normalize();
    }

    public Path store(InputStream input, long declaredSize, long maxSize) throws IOException {
        if (declaredSize < 0 || declaredSize > maxSize) {
            throw new IllegalArgumentException("File size rejected");
        }

        Files.createDirectories(root);
        Path destination = root.resolve(UUID.randomUUID().toString() + ".bin").normalize();

        if (!destination.startsWith(root)) {
            throw new SecurityException("Invalid storage path");
        }

        Files.copy(input, destination, StandardCopyOption.REPLACE_EXISTING);
        return destination;
    }
}
