package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class LogUtil {

    public static void salvarLog(String nomeArquivo, String conteudo) {
        Path caminho = Paths.get("logs", nomeArquivo);
        try {
            Files.createDirectories(caminho.getParent());
            Files.writeString(caminho, conteudo, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("✅ Log salvo em: " + caminho.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("❌ Erro ao salvar log: " + e.getMessage());
        }
    }
}
