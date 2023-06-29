package org.example.model;

import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FileWriter implements DatalakeWriter {
    public void storeData(List<Weather> events) throws IOException {
        String uri = "datalake";
        File folder = new File(uri);
        if (!folder.exists()) {
            if (folder.mkdir()) {
                System.out.println("Se creó el directorio " + folder.getAbsolutePath() + " correctamente");
            }
        }
        for (Weather event : events) {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            LocalDate date = LocalDate.parse(event.getDate(), inputFormat);
            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
            String newDate = date.format(outputFormat);
            String fileName = (uri + "/" + newDate + ".events");
            File day = new File(fileName);
            if (!day.exists()) {
                if (day.createNewFile()) {
                    System.out.println("Se creó el fichero " + day.getAbsolutePath() + " correctamente");
                }
            }
            Gson gson = new Gson();
            String eventLine = gson.toJson(event);
            FileInputStream inputStream = new FileInputStream(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            boolean notWritten = true;
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals(eventLine)) {
                    notWritten = false;
                    break;
                }
            }
            if (notWritten) {
                FileOutputStream outputStream = new FileOutputStream(fileName, true);
                OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                BufferedWriter writer = new BufferedWriter(streamWriter);
                writer.write(eventLine);
                writer.newLine();
                writer.close();
            }
        }
    }
}
