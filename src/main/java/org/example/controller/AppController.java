package org.example.controller;

import org.example.entity.GoodSentence;
import org.example.service.AppService;

public class AppController {


    private final AppService appService;
    public AppController(AppService appService) {
        this.appService = appService;
    }

    public int insert(String content, String writer){
        return appService.insert(content, writer);
    }

    public void update(int num, String content, String writer) {
        appService.update(num, content, writer);
    }

    public int delete(int num) {
        return appService.delete(num);
    }

    public void findAll(){
        appService.findAll();
    }

    public GoodSentence findOne(int num) {
        GoodSentence one = appService.findOne(num);
        if (one == null) {
            System.out.println("해당 명언은 없습니다.");
        }
        return one;
    }
}

