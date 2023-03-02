package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class App {

    List<GoodSentence> list = new ArrayList<>();
    int num = 0;
    final String filePath = "C:/Users/joung/Documents/멋사/textFile.txt";
    BufferedWriter bw = null;
    BufferedReader br = null;
    public void run() {
        try {
            saveList();
        } catch (IOException e) {
            e.getStackTrace();
        }
        num = list.size()+1;
        Scanner sc = new Scanner(System.in);
        System.out.println("==명언 앱==");
        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();
            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                System.out.print("명언 : ");
                String sentence = sc.nextLine();
                System.out.print("작가 : ");
                String writer = sc.nextLine();
                list.add(new GoodSentence(num, sentence, writer));
                System.out.println(num + "번 명언이 등록되었습니다.");

                write(num, sentence, writer);
                num++;
            } else if (cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------------");
                for (GoodSentence gs : list) {
                    System.out.println(gs.toString());
                }
            } else if(cmd.length()==7){
                if (cmd.substring(0, 6).equals("삭제?id=")) {
                    boolean isSentence = false;
                    int delNum = Integer.parseInt(cmd.substring(6));
                    for (GoodSentence gs : list) {
                        if (gs.getNum() == delNum) {
                            list.remove(gs);
                            System.out.println(delNum + "번 명언이 삭제되었습니다.");
                            isSentence = true;
                            break;
                        }
                    }
                    if (!isSentence)
                        System.out.println(delNum + "번 명언은 존재하지 않습니다.");
                } else if (cmd.substring(0, 6).equals("수정?id=")) {
                    GoodSentence curr = null;
                    int updateNum = Integer.parseInt(cmd.substring(6));
                    for (GoodSentence gs : list) {
                        if (gs.getNum() == updateNum) {
                            curr = gs;
                            break;
                        }
                    }
                    if(curr==null){
                        continue;
                    }
                    System.out.println("명령(기존) : " + curr.getSentence());
                    System.out.print("명언 : ");
                    curr.setSentence(sc.nextLine());
                    System.out.println("작가(기존) : " + curr.getWriter());
                    System.out.print("작가 : ");
                    curr.setWriter(sc.nextLine());
                }
            } else {
                continue;
            }
        }
        sc.close();
    }

    private void write(int num, String sentence, String writer) {
        try {
            bw = new BufferedWriter(new FileWriter(filePath, true));
            bw.write(num+" "+ writer +" "+ sentence);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    private void saveList() throws IOException {
        StringTokenizer st;
        String read;
        try {
            br = new BufferedReader(new FileReader(filePath));
            while(true){
                read = br.readLine();
                if(read==null)
                    break;
                st = new StringTokenizer(read);
                list.add(new GoodSentence(Integer.parseInt(st.nextToken()), st.nextToken(), st.nextToken()));
            }

        } catch (FileNotFoundException e) {
            e.getStackTrace();
        } catch (Exception e) {
            e.getStackTrace();
        } finally{
            if (br != null) {
                br.close();
            }
        }
    }
}
