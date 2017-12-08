package com.github.irvingryan.dagger2demo.main;


import com.github.irvingryan.dagger2demo.mvp.MainPresenter;
import com.github.irvingryan.dagger2demo.mvp.MainView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

/**
 * Created by yanwentao on 2017/12/7.
 */

public class MainPresenterTest {

    @Mock
    private MainView mainView;

    @Before
    public void setupMainPresenter() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doSomething() {
        MainPresenter mainPresenter = new MainPresenter(mainView);
        mainPresenter.doSomething();
        verify(mainView).somethingDone(""); // saved to the model
    }
}
