package exercise;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

class App {
    public static Car extract(Path path) throws IOException {
        String content = Files.readString(path);
        return Car.unserialize(content);
    }
    public static void save(Path path, Car car) throws IOException {
        String serialized = car.serialize();
        byte[] bytes = serialized.getBytes(StandardCharsets.UTF_8);
        Files.write(path, bytes);


    }
}
