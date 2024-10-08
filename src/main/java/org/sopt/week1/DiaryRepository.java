package org.sopt.week1;

import org.sopt.week1.Main.UI.InvalidInputException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class DiaryRepository {
    private final Map<Long, String> storage = new ConcurrentHashMap<>();
    private final AtomicLong numbering = new AtomicLong();

    void save(final Diary diary) {
        final long id = numbering.addAndGet(1);

        storage.put(id, diary.getBody());
    }

    List<Diary> findAll() {
        List<Diary> diaryList = new ArrayList<>();

        for (long index = 1; index <= numbering.get(); index++) {
            final String body = storage.get(index);

            if (body != null) {
                diaryList.add(Diary.of(index, body));
            }
        }

        return diaryList;
    }

    public void patch(Long id, String body) {
        validateIdExists(id);
        storage.put(id, body);
    }

    public void delete(Long id) {
        validateIdExists(id);
        storage.remove(id);
    }

    private void validateIdExists(Long id) {
        if (storage.get(id) == null) {
            throw new InvalidInputException("존재하지 않는 id 값입니다.");
        }
    }
}
