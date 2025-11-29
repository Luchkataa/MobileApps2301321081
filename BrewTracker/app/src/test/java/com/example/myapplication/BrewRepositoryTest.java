package com.example.myapplication;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.ExecutorService;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BrewRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private BrewDao mockDao;

    @Mock
    private ExecutorService mockExecutor;

    @Before
    public void setup() {
        doAnswer(invocation -> {
            ((Runnable) invocation.getArgument(0)).run();
            return null;
        }).when(mockExecutor).execute(any(Runnable.class));
    }

    @Test
    public void insert_callsDaoInsert() {
        Brew testBrew = new Brew("Test IPA", "Heavy hops", 4.5f);
        BrewRepository repository = new BrewRepository(mockDao, mockExecutor);

        repository.insert(testBrew);

        verify(mockDao, times(1)).insert(testBrew);
    }

    @Test
    public void update_callsDaoUpdate() {
        Brew testBrew = new Brew("Updated Brew", "New Notes", 4.0f);
        BrewRepository repository = new BrewRepository(mockDao, mockExecutor);

        repository.update(testBrew);

        verify(mockDao, times(1)).update(testBrew);
    }

    @Test
    public void delete_callsDaoDelete() {
        Brew testBrew = new Brew("Brew to Delete", "Expired", 1.0f);
        BrewRepository repository = new BrewRepository(mockDao, mockExecutor);

        repository.delete(testBrew);

        verify(mockDao, times(1)).delete(testBrew);
    }

    @Test
    public void getAllBrews_callsDaoGetAllBrews() {
        BrewRepository repository = new BrewRepository(mockDao, mockExecutor);

        repository.getAllBrews();

        verify(mockDao, times(1)).getAllBrews();
    }
}