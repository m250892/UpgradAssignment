package com.manoj.upgradassignment;

import com.manoj.upgradassignment.presenter.MovieGridPresenter;
import com.manoj.upgradassignment.view.MovieGridView;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by manoj on 20/06/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieGridPresenterTest {

    @Mock
    private MovieGridView movieGridView;
    private MovieGridPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new MovieGridPresenter(movieGridView);
    }

}