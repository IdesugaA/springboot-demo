package com.demo.freemarkerdemo.controller;

import com.demo.freemarkerdemo.dto.PersonnelSaveDto;
import com.demo.freemarkerdemo.dto.PersonnelDeleteDto;
import com.demo.freemarkerdemo.dto.PersonnelQueryDto;
import com.demo.freemarkerdemo.vo.PersonnelQueryVo;
import com.demo.freemarkerdemo.service.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personnel")
public class PersonnelController {

    @Autowired
    private PersonnelService personnelService;


}
