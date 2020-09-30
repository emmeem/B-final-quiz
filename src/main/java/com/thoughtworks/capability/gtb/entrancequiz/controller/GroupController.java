package com.thoughtworks.capability.gtb.entrancequiz.controller;

import com.thoughtworks.capability.gtb.entrancequiz.domain.GtbGroup;
import com.thoughtworks.capability.gtb.entrancequiz.dto.ChangeGroupNameDto;
import com.thoughtworks.capability.gtb.entrancequiz.dto.GroupDto;
import com.thoughtworks.capability.gtb.entrancequiz.service.GroupService;
import com.thoughtworks.capability.gtb.entrancequiz.utils.ConvertTool;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {

        this.groupService = groupService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GroupDto> getGroups() {
        List<GtbGroup> gtbGroups = groupService.getGroups();
        return ConvertTool.convertList(gtbGroups, GroupDto.class);
    }

    @PostMapping("/auto-grouping")
    @ResponseStatus(HttpStatus.CREATED)
    public List<GroupDto> autoGrouping() {
        List<GtbGroup> groupingResult = groupService.autoGrouping();
        return ConvertTool.convertList(groupingResult, GroupDto.class);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateGroupName(@RequestBody ChangeGroupNameDto changeGroupNameDto, @PathVariable long id) {
        String newName = changeGroupNameDto.getName();
        groupService.rename(newName, id);
    }

}
