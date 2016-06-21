package com.manoj.upgradassignment;

import com.manoj.upgradassignment.presenter.MovieGridPresenter;
import com.manoj.upgradassignment.view.MovieGridView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by manoj on 21/06/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    MovieGridView movieView;
    @Mock
    MovieDatabase movieDatabase;

    private MovieGridPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new MovieGridPresenter(movieView);
    }

    @Test
    public void onFirstTimeDataLoadShouldCallShowLoading() {
        /*when(movieDatabase.isDataComplete()).thenReturn(false);
        when(movieDatabase.getLastKnowPage()).thenReturn(0);
        presenter.loadData();
        verify(movieView).showLoading();*/
    }
}
