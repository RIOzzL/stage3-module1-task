package com.mjc.school.repository.utils;

import com.mjc.school.repository.models.Author;
import com.mjc.school.repository.models.News;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DataSource {

    private static DataSource dataSource;
    private List<News> newsList;
    private List<Author> authorList;

    private Long newsId;
    private Long authorId;

    private DataSource() {
        newsList = new ArrayList<>();
        authorList = new ArrayList<>();
        initAuthors();
        initNews();
    }

    public static DataSource getInstance() {
        if (dataSource == null) {
            dataSource = new DataSource();
            return dataSource;
        }
        return dataSource;
    }

    private void initNews() {
        List<String> newsTitles = FileResourcesUtils.getDataFromResourceFiles("news.txt");
        List<String> newsContents = FileResourcesUtils.getDataFromResourceFiles("content.txt");
        for (int id = 1; id <= 20; id++) {
            newsList.add(new News((long) id, newsTitles.get(id - 1), newsContents.get(id - 1), LocalDateTime.now(),
                    LocalDateTime.now(), authorList.get(id - 1).getId()));
        }
        newsId = (long) newsList.size();
    }

    private void initAuthors() {
        List<String> newsAuthors = FileResourcesUtils.getDataFromResourceFiles("author.txt");
        for (int id = 1; id <= 20; id++) {
            authorList.add(new Author((long) id, newsAuthors.get(id - 1).trim()));
        }
        authorId = (long) authorList.size();
    }

    public Long increaseNewsId() {
        return ++newsId;
    }
}
