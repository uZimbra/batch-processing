package com.rodrigo.batch_processing.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import com.rodrigo.batch_processing.domain.CardType;

public class FileUtils {

  public static String fileName(Long product, Long variant, Long batchNumber, String processingDate) {
    return "P_" + product + "_V_" + variant + "_BN_" + batchNumber + "_D_" + processingDate + ".OUT";
  }

  public static File checkIfFileExists(CardType item, Long batchNumber, String processingDate) throws IOException {
    String fileName = FileUtils.fileName(item.getProduct(), item.getVariant(), batchNumber, processingDate);

    var path = Paths.get("output");

    if (!path.toFile().exists()) {
      path.toFile().mkdirs();
    }

    var file = new File(String.valueOf(path.resolve(fileName)));

    if (!file.exists() && (file.createNewFile())) {
      var writer = new FileWriter(file);
      writer.append(Integer.valueOf(0).toString());
      writer.append(item.getProduct().toString());
      writer.append(item.getVariant().toString());
      writer.append(processingDate);
      writer.append(item.getDescription());
      writer.append(System.lineSeparator());
      writer.close();
      return file;
    }

    return file;
  }

  public static File resolveFilePath(String filename) {
    return new File(String.valueOf(Paths.get("output").resolve(filename)));
  }

  public static File[] listFilesInOutputDirectory() {
    return new File(Paths.get("output").toFile().getAbsolutePath()).listFiles();
  }

}
