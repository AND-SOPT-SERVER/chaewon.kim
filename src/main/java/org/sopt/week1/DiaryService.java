package org.sopt.week1;

import org.sopt.week1.Main.UI.InvalidInputException;
import org.sopt.week1.Main.UI.LimitExceededException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class DiaryService {
    private final DiaryRepository diaryRepository = new DiaryRepository();

    void postDiary(String body) {
        final Diary diary = Diary.of(null, body);
        diaryRepository.save(diary);
    }

    List<Diary> getDiaryList() {
        return diaryRepository.findAll();
    }

    public void patchDiary(Long id, String body) {
        validateExist(id);
        Diary diary = diaryRepository.findById(id);
        updatePatchCountAndDate(diary);
        diary.updateBody(body); //일기 내용 수정
        diaryRepository.patch(id, diary);
    }

    public void deleteDiary(Long id) {
        validateExist(id);
        diaryRepository.delete(id);
    }

    public List<Diary> getTrashList() {
        return diaryRepository.findAllInTrash();
    }

    public void restoreDiary(Long id) {
        validateExistInTrash(id);
        diaryRepository.restore(id);
    }

    private void validateExist(Long id) {
        if(!diaryRepository.existsById(id)) {
            throw new InvalidInputException("존재하지 않는 id 값입니다.");
        }
    }

    private void validateExistInTrash(Long id) {
        if(!diaryRepository.existsInTrashById(id)) {
            throw new InvalidInputException("존재하지 않는 id 값입니다.");
        }
    }

    private void updatePatchCountAndDate(Diary diary) {
        if (Objects.equals(diary.getUpdatedAt(), LocalDate.now())) { //같은 날 수정하는 경우
            if (diary.getPatchCount() == 2) {
                throw new LimitExceededException("하루에 일기를 2번 이상 수정할 수 없습니다.");
            }
            diary.incrementPatchCount();
        } else {
            diary.updateUpdatedAt(); //수정 날짜 업데이트
            diary.resetPatchCount(); //수정 횟수 업데이트
        }
    }
}
