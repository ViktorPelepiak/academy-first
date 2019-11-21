package io;

import adapter.LocalTimeJSONAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import service.Schedule;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;

public class JSONConverter {
    public static void serialise(String path, Schedule schedule) throws IOException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalTime.class, new LocalTimeJSONAdapter())
                .create();
        File file = new File(path);
        FileWriter fw = new FileWriter(file);
        fw.write(gson.toJson(schedule));
        fw.close();
    }

    public static Schedule deserialize(String path) throws IOException, JAXBException {
        return new Gson().fromJson(new FileReader(path), Schedule.class);
    }
}
