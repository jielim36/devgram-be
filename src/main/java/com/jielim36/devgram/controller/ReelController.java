package com.jielim36.devgram.controller;

import com.jielim36.devgram.CustomAnnotation.UserIdRequired.UserIdRequired;
import com.jielim36.devgram.DTO.ReelDTO;
import com.jielim36.devgram.common.ResultResponse;
import com.jielim36.devgram.entity.PostEntity;
import com.jielim36.devgram.entity.ReelEntity;
import com.jielim36.devgram.enums.ResultCode;
import com.jielim36.devgram.service.ReelService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reel")
public class ReelController {

    private final ReelService reelService;

    public ReelController(ReelService reelService) {
        this.reelService = reelService;
    }

    @PostMapping
    public ResultResponse createReel(@RequestBody ReelEntity reelEntity) {
        boolean isSuccess = reelService.insertReel(reelEntity);

        if (isSuccess) {
            return ResultResponse.success(true);
        } else {
            return ResultResponse.failure(ResultCode.INTERNAL_SERVER_ERROR, "Reel creation failed");
        }
    }

    @UserIdRequired
    @GetMapping("/popular/{page}")
    public ResultResponse getPopularReels(@PathVariable Integer page, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        ReelDTO[] reelDTOs = reelService.selectPopularReels(userId, page);

        if(reelDTOs == null) {
            return ResultResponse.failure(ResultCode.INTERNAL_SERVER_ERROR, "Failed to retrieve popular reels");
        }

        return ResultResponse.success(reelDTOs);
    }

}
