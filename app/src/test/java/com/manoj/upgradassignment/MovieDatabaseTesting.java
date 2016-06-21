package com.manoj.upgradassignment;

import com.manoj.upgradassignment.model.Movie;
import com.manoj.upgradassignment.model.MovieListPage;
import com.manoj.upgradassignment.view.MovieDatabaseView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by manoj on 22/06/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieDatabaseTesting {

    private MovieDatabaseView movieDatabase;

    @Before
    public void setUp() throws Exception {
        movieDatabase = MovieDatabase.getInstance();
    }

    @Test
    public void clearDBTest() {
        movieDatabase.clearData();
        assertEquals(movieDatabase.getDataSize(), 0);
        assertEquals(movieDatabase.getLastKnowPage(), 0);
    }

    @Test
    public void testInsertData() {
        movieDatabase.clearData();
        MovieListPage page = getDummyPage(1);
        movieDatabase.insertPageData(page);
        assertEquals(movieDatabase.getDataSize(), 5);
        assertEquals(movieDatabase.getLastKnowPage(), 1);
    }


    @Test
    public void testNextPageData() {
        movieDatabase.clearData();
        MovieListPage page = getDummyPage(1);
        movieDatabase.insertPageData(page);

        assertEquals(movieDatabase.getNextPage(), 2);
    }

    @Test
    public void testInsertPageDataMoreThanLastPage() {
        movieDatabase.clearData();
        //data not insert
        MovieListPage page = getDummyPage(2);
        movieDatabase.insertPageData(page);

        assertEquals(movieDatabase.getDataSize(), 0);
        assertEquals(movieDatabase.getLastKnowPage(), 0);
    }

    @Test
    public void testInsertMultiSequentialPage() {
        movieDatabase.clearData();
        MovieListPage page = getDummyPage(1);
        movieDatabase.insertPageData(page);
        page = getDummyPage(2);
        movieDatabase.insertPageData(page);
        page = getDummyPage(3);
        movieDatabase.insertPageData(page);

        assertEquals(movieDatabase.getDataSize(), 15);
        assertEquals(movieDatabase.getLastKnowPage(), 3);
    }


    @Test
    public void testDataSetComplete() {
        movieDatabase.clearData();
        MovieListPage page = getDummyPage(1);
        movieDatabase.insertPageData(page);
        page = getDummyPage(2);
        movieDatabase.insertPageData(page);
        page = getDummyPage(3);
        movieDatabase.insertPageData(page);
        page = getDummyPage(4);
        movieDatabase.insertPageData(page);

        //total result also 20
        assertEquals(movieDatabase.getDataSize(), 20);
        assertEquals(movieDatabase.getLastKnowPage(), 4);
        assertTrue(movieDatabase.isDataComplete());
    }


    //dummy page size is 5
    public MovieListPage getDummyPage(int pageNo) {
        MovieListPage page = new MovieListPage();
        List<Movie> data = new ArrayList<>();
        data.add(new Movie());
        data.add(new Movie());
        data.add(new Movie());
        data.add(new Movie());
        data.add(new Movie());
        page.setPage(pageNo);
        page.setTotalPages(4);
        page.setTotalResults(20);
        page.setMovies(data);
        return page;
    }


    public void testOrderSetTest() {
        movieDatabase.clearData();
        movieDatabase.setSortOrder(0);
        assertEquals(movieDatabase.getSortOrder(), 0);
        movieDatabase.setSortOrder(1);
        assertEquals(movieDatabase.getSortOrder(), 1);
    }

}
