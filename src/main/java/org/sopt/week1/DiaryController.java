package org.sopt.week1;

import org.sopt.week1.Main.UI.InvalidInputException;

import java.util.List;

public class DiaryController {
    private Status status = Status.READY;
    private final DiaryService diaryService = new DiaryService();

    Status getStatus() {
        return status;
    }

    void boot() {
        this.status = Status.RUNNING;
    }

    void finish() {
        this.status = Status.FINISHED;
    }

    //APIS
    final List<Diary> getList() {
        return diaryService.getDiaryList();
    }

    final void post(final String body) {
        validateBody(body);
        diaryService.postDiary(body);
    }

    final void patch(final String id, final String body) {
        validateType(id);
        validateBody(body);
        diaryService.patchDiary(Long.parseLong(id), body);
    }

    final void delete(final String id) {
        validateType(id);
        diaryService.deleteDiary(Long.parseLong(id));
    }

    private void validateBody(String body) {
        if (body.length() > 30) {
            throw new InvalidInputException("일기 글자수는 30글자를 넘을 수 없습니다.");
        }
    }

    private void validateType(String id) {
        try {
            Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("숫자를 입력해주세요.");
        }
    }

    enum Status {
        READY,
        RUNNING,
        FINISHED,
        ERROR,
    }
}
