package org.sopt.diary.api.controller;

import org.sopt.diary.api.dto.request.DiaryCreateDto;
import org.sopt.diary.api.dto.request.DiaryUpdateDto;
import org.sopt.diary.api.dto.response.DiaryDetailResponse;
import org.sopt.diary.api.dto.response.DiaryListResponse;
import org.sopt.diary.api.dto.response.DiaryResponse;
import org.sopt.diary.api.service.Diary;
import org.sopt.diary.api.service.DiaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/luckybicky")
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    // 일기 작성
    @PostMapping("/diaries")
    public ResponseEntity<DiaryResponse> createDiary(
            @RequestBody final DiaryCreateDto diaryCreateDto
    ) {
        return ResponseEntity.ok(diaryService.createDiary(diaryCreateDto));
    }

    // 일기 상세 조회
    @GetMapping("/diaries/{diaryId}")
    public ResponseEntity<DiaryDetailResponse> getDiary(
            @PathVariable final Long diaryId
    ) {
        return ResponseEntity.ok(diaryService.getDiary(diaryId));
    }

    // 일기 목록 조회
    @GetMapping("/diaries")
    public ResponseEntity<DiaryListResponse> getDiaryList() {
        return ResponseEntity.ok(diaryService.getDiaryList());
    }

    // 일기 수정
    @PatchMapping("/diaries/{diaryId}")
    public ResponseEntity<Void> updateDiary(
            @PathVariable final Long diaryId,
            @RequestBody final DiaryUpdateDto diaryUpdateDto
    ) {
        diaryService.updateDiary(diaryId, diaryUpdateDto);
        return ResponseEntity.ok().build();
    }

    // 일기 삭제
    @DeleteMapping("/diaries/{diaryId}")
    public ResponseEntity<DiaryResponse> deleteDiary(
            @PathVariable final Long diaryId
    ) {
        diaryService.deleteDiary(diaryId);
        return ResponseEntity.ok().build();
    }
}
