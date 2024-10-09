package org.sopt.week1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class DiaryRepository {
    private final Map<Long, Diary> storage = new ConcurrentHashMap<>();
    private final Map<Long, Diary> trash = new ConcurrentHashMap<>();
    private final AtomicLong numbering = new AtomicLong();

    void save(final Diary diary) {
        final long id = numbering.addAndGet(1);

        storage.put(id, diary);
    }

    public Diary findById(Long id) {
        return storage.get(id);
    }

    List<Diary> findAll() {
        List<Diary> diaryList = new ArrayList<>();

        for (long index = 1; index <= numbering.get(); index++) {
            final String body = storage.get(index).getBody();

            System.out.println(storage.get(index).getBody() + storage.get(index).getUpdatedAt() + storage.get(index).getPatchCount());

            if (body != null) {
                diaryList.add(Diary.of(index, body));
            }
        }

        return diaryList;
    }

    public List<Diary> findAllInTrash() {
        List<Diary> trashList = new ArrayList<>();

        // trash의 각 항목을 돌면서 리스트에 추가함
        for (Map.Entry<Long, Diary> entry : trash.entrySet()) {
            Long id = entry.getKey();
            String body = entry.getValue().getBody();
            trashList.add(Diary.of(id, body));
        }

        return trashList;
    }

    public void patch(Long id, Diary diary) {
        storage.put(id, diary);
    }

    public void delete(Long id) {
        trash.put(id, storage.get(id));
        storage.remove(id);
    }

    public void restore(Long id) {
        storage.put(id, trash.get(id));
        trash.remove(id);
    }

    public boolean existsById(Long id) {
        return storage.containsKey(id);
    }

    public boolean existsInTrashById(Long id) {
        return trash.containsKey(id);
    }
}
