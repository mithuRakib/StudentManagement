package com.example.studentmanagement.tasksExecutors;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BackgroundTasksExecutor {
    private static final Object LOCK = new Object();
    private static BackgroundTasksExecutor bTExecutorInstance;

    private final Executor diskIO;
    private final Executor networkIO;
    private final Executor mainThread;

    public static BackgroundTasksExecutor getInstance() {
        if (bTExecutorInstance == null) {
            synchronized (LOCK) {
                bTExecutorInstance = new BackgroundTasksExecutor(
                        Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(3),
                        new MainThreadExecutor()
                );
            }
        }
        return bTExecutorInstance;
    }

    public BackgroundTasksExecutor(Executor diskIO, Executor networkIO, Executor mainThread) {
        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.mainThread = mainThread;
    }

    public Executor getDiskIO() {
        return diskIO;
    }

    public Executor getNetworkIO() {
        return networkIO;
    }

    public Executor getMainThread() {
        return mainThread;
    }

    private static class MainThreadExecutor implements Executor {
        private Handler handler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable command) {
            handler.post(command);
        }
    }
}
