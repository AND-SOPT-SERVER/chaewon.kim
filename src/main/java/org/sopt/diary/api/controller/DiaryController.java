package org.sopt.diary.api.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.sopt.diary.api.dto.ResponseDto;
import org.sopt.diary.api.dto.request.Diary.DiaryCreateDto;
import org.sopt.diary.api.dto.request.Diary.DiaryUpdateDto;
import org.sopt.diary.api.dto.response.Diary.DiaryDetailResponse;
import org.sopt.diary.api.dto.response.Diary.DiaryListResponse;
import org.sopt.diary.api.dto.response.Diary.DiaryResponse;
import org.sopt.diary.api.service.DiaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/luckybicky")
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    // 일기 작성
    @PostMapping("/diaries")
    public ResponseEntity<ResponseDto<DiaryResponse>> createDiary(
            @RequestHeader("Member-Id") Long memberId,
            @Valid @RequestBody final DiaryCreateDto diaryCreateDto
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDto.success(
                                diaryService.createDiary(
                                        memberId,
                                        diaryCreateDto.getTitle(),
                                        diaryCreateDto.getContent(),
                                        diaryCreateDto.getCategory(),
                                        diaryCreateDto.isPublic()
                                )
                        )
                );
    }

    // 일기 상세 조회
    @GetMapping("/diaries/{diaryId}")
    public ResponseEntity<ResponseDto<DiaryDetailResponse>> getDiary(
            @PathVariable @Min(value = 1L, message = "diaryId는 양수여야 합니다.") final Long diaryId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.success(diaryService.getDiary(diaryId)));
    }

    // 일기 목록 조회
    @GetMapping("/diaries")
    public ResponseEntity<ResponseDto<DiaryListResponse>> getDiaryList() {
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.success(diaryService.getDiaryList()));
    }

    // 일기 수정
    @PatchMapping("/diaries/{diaryId}")
    public ResponseEntity<ResponseDto<Void>> updateDiary(
            @PathVariable @Min(value = 1L, message = "diaryId는 양수여야 합니다.") final Long diaryId,
            @Valid @RequestBody final DiaryUpdateDto diaryUpdateDto
    ) {
        diaryService.updateDiary(diaryId, diaryUpdateDto.getTitle(), diaryUpdateDto.getContent());
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.success());
    }

    // 일기 삭제
    @DeleteMapping("/diaries/{diaryId}")
    public ResponseEntity<ResponseDto<Void>> deleteDiary(
            @PathVariable @Min(value = 1L, message = "diaryId는 양수여야 합니다.") final Long diaryId
    ) {
        diaryService.deleteDiary(diaryId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.success());
    }
}
