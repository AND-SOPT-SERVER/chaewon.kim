package org.sopt.week1;

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
        diaryRepository.patch(id, body);
    }

    public void deleteDiary(Long id) {
        diaryRepository.delete(id);
    }
}
