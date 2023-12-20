package com.rentsky.controller;

import com.rentsky.entity.Ski;
import com.rentsky.service.SkiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/skis")
public class SkiController {
    private final SkiService skiService;
    @GetMapping
    public List<Ski> getSkis(@RequestParam(required = false, defaultValue = "0")
                             Integer page,
                             @RequestParam(required = false, defaultValue = "50")
                             Integer pageSize) {
        return skiService.getSkis(page, pageSize);
    }
}
