package org.sopt.week1;

import org.sopt.week1.Main.UI.InvalidInputException;

import java.util.List;

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
        diaryRepository.patch(id, body);
    }

    public void deleteDiary(Long id) {
        validateExist(id);
        diaryRepository.delete(id);
    }

    private void validateExist(Long id) {
        if(!diaryRepository.existsById(id)) {
            throw new InvalidInputException("존재하지 않는 id 값입니다.");
        }
    }
}
