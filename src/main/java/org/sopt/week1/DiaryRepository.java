package org.sopt.week1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class DiaryRepository {
    private static final String STORAGE_FILE = "storage.json";
    private static final String TRASH_FILE = "trash.json";
    private static final String NUMBERING_FILE = "numbering.json";

    private final Map<Long, Diary> storage = new ConcurrentHashMap<>();
    private final Map<Long, Diary> trash = new ConcurrentHashMap<>();
    private final AtomicLong numbering = new AtomicLong();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public DiaryRepository() {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        loadDiaries();
        loadTrash();
        loadNumbering();
    }

    void save(final String body) {
        final long id = numbering.addAndGet(1);

        storage.put(id, Diary.of(id, body));
        saveDiaries();
        saveNumbering();
    }

    public Diary findById(Long id) {
        return storage.get(id);
    }

    List<Diary> findAll() {
        List<Diary> diaryList = new ArrayList<>();

        for (long index = 1; index <= numbering.get(); index++) {
            Diary diary = storage.get(index);

            if (diary != null) {
                diaryList.add(diary);
            }
        }

        return diaryList;
    }

    public List<Diary> findAllInTrash() {
        List<Diary> trashList = new ArrayList<>();

        // trash의 각 항목을 돌면서 리스트에 추가함
        for (Map.Entry<Long, Diary> entry : trash.entrySet()) {
            trashList.add(entry.getValue());
        }

        return trashList;
    }

    public void patch(Long id, Diary diary) {
        storage.put(id, diary);
        saveDiaries();
    }

    public void delete(Long id) {
        trash.put(id, storage.get(id));
        storage.remove(id);
        saveDiaries();
        saveTrash();
    }

    public void restore(Long id) {
        storage.put(id, trash.get(id));
        trash.remove(id);
        saveDiaries();
        saveTrash();
    }

    public boolean existsById(Long id) {
        return storage.containsKey(id);
    }

    public boolean existsInTrashById(Long id) {
        return trash.containsKey(id);
    }

    private void saveDiaries() {
        try {
            objectMapper.writeValue(new File(STORAGE_FILE), storage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDiaries() {
        try {
            Map<Long, Diary> loadedStorage = objectMapper.readValue(new File(STORAGE_FILE), new TypeReference<Map<Long, Diary>>() {});
            storage.putAll(loadedStorage);
        } catch (IOException e) {
            System.out.println("기존 일기 데이터를 불러오지 못했습니다. 새로 시작합니다.");
        }
    }

    private void saveTrash() {
        try {
            objectMapper.writeValue(new File(TRASH_FILE), trash);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTrash() {
        try {
            Map<Long, Diary> loadedTrash = objectMapper.readValue(new File(TRASH_FILE), new TypeReference<Map<Long, Diary>>() {});
            trash.putAll(loadedTrash);
        } catch (IOException e) {
            System.out.println("기존 휴지통 데이터를 불러오지 못했습니다. 새로 시작합니다.");
        }
    }

    private void saveNumbering() {
        try {
            objectMapper.writeValue(new File(NUMBERING_FILE), numbering);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadNumbering() {
        try {
            AtomicLong loadedNumbering = objectMapper.readValue(new File(NUMBERING_FILE), AtomicLong.class);
            numbering.set(loadedNumbering.get());
        } catch (IOException e) {
            System.out.println("기존 번호 데이터를 불러오지 못했습니다. 새로 시작합니다.");
        }
    }
}
