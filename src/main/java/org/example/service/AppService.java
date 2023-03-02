package org.example.service;

import org.example.entity.GoodSentence;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class AppService {

    private final List<GoodSentence> list = new ArrayList<>();
    private int n = 1;
    static final String filePath = "C:/Users/joung/Documents/멋사/textFile.txt";
    private BufferedWriter bw = null;
    private BufferedReader br = null;
    private JSONObject obj = null;


    //curd
    public int insert(String content, String writer) {
        GoodSentence goodSentence = new GoodSentence(n, content, writer);
        list.add(goodSentence);

        n++;
        save();
        writeJson();
        return n - 1;
    }

    public int update(int num, String content, String writer) {
        GoodSentence curr = null;
        for (GoodSentence goodSentence : list) {
            if (goodSentence.getNum() == num) {
                curr = goodSentence;
                break;
            }
        }
        if (curr == null) {
            return -1;
        }
        curr.setWriter(writer);
        curr.setSentence(content);
        save();
        writeJson();
        return 1;
    }

    public int delete(int num) {
        for (GoodSentence goodSentence : list) {
            if (goodSentence.getNum() == num) {
                list.remove(goodSentence);
                return 1;
            }
        }
        save();
        writeJson();
        return -1;
    }

    public void findAll() {
        StringTokenizer st;
        try {
            br = new BufferedReader(new FileReader(filePath));

            while (true) {
                String str = br.readLine();
                if (str == null)
                    break;
                st = new StringTokenizer(str);
                System.out.println(st.nextToken() + " / " + st.nextToken() + " / " + st.nextToken());
            }

            br.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public GoodSentence findOne(int num) {
        for (GoodSentence goodSentence : list) {
            if (goodSentence.getNum() == num) {
                return goodSentence;
            }
        }
        return null;
    }

    private void save() {
        try {
            bw = new BufferedWriter(new FileWriter(filePath, false));
            for (GoodSentence gs : list) {
                bw.write(gs.getNum() + " " + gs.getWriter() + " " + gs.getSentence());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    private void writeJson() {
        JSONObject obj;
        JSONArray jsonArray = new JSONArray();
        for (GoodSentence gs : list) {
            obj = new JSONObject();
            obj.put("id", gs.getNum());
            obj.put("content", gs.getSentence());
            obj.put("author", gs.getWriter());
            jsonArray.add(obj);
        }
        try {
            FileWriter file = new FileWriter("src/jsonfile.json", false);
            jsonArray.writeJSONString(file);

            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

