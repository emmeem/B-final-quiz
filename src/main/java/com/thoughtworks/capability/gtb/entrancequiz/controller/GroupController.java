package com.thoughtworks.capability.gtb.entrancequiz.controller;

import com.thoughtworks.capability.gtb.entrancequiz.domain.GtbGroup;
import com.thoughtworks.capability.gtb.entrancequiz.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
//TODO GTB：自动分组和组重命名的功能缺失
@RestController
@CrossOrigin
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {

        this.groupService = groupService;
    }

    //TODO GTB：为啥用PostMapping？
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GtbGroup> getGroups() {
        return groupService.getGroups();
    }

}
