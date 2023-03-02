package org.example.system;

import org.example.controller.AppController;
import org.example.entity.GoodSentence;
import org.example.service.AppService;

import java.util.Scanner;

public class AppSystem {

    AppController appController = new AppController(new AppService());

    public void run() {

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

                int addNum = appController.insert(sentence, writer);
                System.out.println(addNum + "번 명언이 등록되었습니다.");
            } else if (cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------------");
                appController.findAll();

            } else if(cmd.length()==7){
                if (cmd.substring(0, 6).equals("삭제?id=")) {
                    int delNum = Integer.parseInt(cmd.substring(6));
                    int success = appController.delete(delNum);
                    if (success==-1)
                        System.out.println(delNum + "번 명언은 존재하지 않습니다.");
                } else if (cmd.substring(0, 6).equals("수정?id=")) {
                    int updateNum = Integer.parseInt(cmd.substring(6));
                    GoodSentence find = appController.findOne(updateNum);
                    System.out.println("명령(기존) : " + find.getSentence());
                    System.out.print("명언 : ");
                    String content = sc.nextLine();
                    System.out.println("작가(기존) : " + find.getWriter());
                    System.out.print("작가 : ");
                    String writer = sc.nextLine();

                    appController.update(updateNum, content, writer);
                }
            } else {
                continue;
            }
        }
        sc.close();
    }
}
