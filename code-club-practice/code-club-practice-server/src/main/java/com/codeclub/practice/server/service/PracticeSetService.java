package com.codeclub.practice.server.service;

import com.codeclub.practice.api.vo.SpecialPracticeVO;

import java.util.List;

public interface PracticeSetService {

    /**
     * 获取专项练习内容
     *
     * @return
     */
    List<SpecialPracticeVO> getSpecialPracticeContent();

}
