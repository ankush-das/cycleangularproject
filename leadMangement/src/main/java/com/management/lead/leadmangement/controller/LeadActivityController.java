package com.management.lead.leadmangement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.lead.leadmangement.dto.ActivityDTO;
import com.management.lead.leadmangement.entity.ActivityLog;
import com.management.lead.leadmangement.entity.LeadActivity;
import com.management.lead.leadmangement.services.ActivityService;

import jakarta.persistence.EntityNotFoundException;

@CrossOrigin
@RestController
@RequestMapping("/api/activities")
public class LeadActivityController {

    private ActivityService activityService;

    public LeadActivityController(@Autowired ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping("/create/{capturedLeadId}")
    public ResponseEntity<LeadActivity> createActivity(@PathVariable long capturedLeadId,
            @RequestBody ActivityDTO activityDTO) {

        if (activityDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            LeadActivity createdActivity = activityService.createActivity(capturedLeadId, activityDTO);

            return new ResponseEntity<>(createdActivity, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all/{capturedLeadId}")
    public ResponseEntity<List<LeadActivity>> getAllActivities(@PathVariable long capturedLeadId) {
        try {
            List<LeadActivity> activities = activityService.allActivity(capturedLeadId);
            return new ResponseEntity<>(activities, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/log/{capturedLeadId}")
    public ResponseEntity<List<ActivityLog>> getAllLog(@PathVariable long capturedLeadId) {
        try {
            List<ActivityLog> logs = activityService.allLogs(capturedLeadId);
            return new ResponseEntity<>(logs, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
