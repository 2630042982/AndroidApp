package com.jnu.student;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DataBank_total {
    final String DATA_FILENAME="scoreList0.data";
    public ArrayList<ScoreList> LoadTaskItems(Context context) {
        ArrayList<ScoreList> data = new ArrayList<>();
        try {
            FileInputStream fileIn = context.openFileInput(DATA_FILENAME);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            data = (ArrayList<ScoreList>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            Log.d("Serialization", "Data loaded successfully.item count: " + data.size());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            Log.d("test", "无法加载得分数据" + data.size());

        }
        return data;
    }

    public void SaveTaskItems(Context context,ArrayList<ScoreList> taskList) {
        try {
            FileOutputStream fileOut = context.openFileOutput(DATA_FILENAME,Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(taskList) ;
            out.close();
            fileOut.close();
            Log.d( "serialization", "Data is serialized and saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}