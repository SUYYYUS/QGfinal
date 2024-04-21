package com.suyyyus.utils.DatabaseBackup;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

//"cmd /c " +
public class DatabaseBackup {
    public static void backupByMysqldump() throws IOException {
        String command = "mysqldump -uroot -pcsy090944CSY db01>C:\\Users\\28937\\Desktop\\hhh.sql";
        System.out.println(5556);
        ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", command);
        System.out.println(87654);
        processBuilder.inheritIO(); // 将子进程的输入、输出流与父进程绑定
        System.out.println(332332);
        Process process = processBuilder.start();
        System.out.println("asfdghfgd");
//        Runtime.getRuntime().exec(command);
        try {
            process.waitFor(); // 等待子进程执行完成
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void scheduleBackup() {
        System.out.println(5);
//        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
//        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        System.out.println(6);
//        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        // 创建自定义的ThreadFactory，指定线程名称
        ThreadFactory threadFactory = new CustomThreadFactory("DatabaseBackupThread");
        System.out.println(1);
        // 创建线程池，使用带ThreadFactory的构造函数
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1, threadFactory);
        System.out.println(2);
        executor.scheduleAtFixedRate(() -> {
            try {
                System.out.println(234567);
                DatabaseBackup.backupByMysqldump();
                System.out.println("成功");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(88888);
            }

        }, 0, 3, TimeUnit.MINUTES);
        System.out.println(7);
    }


    static class CustomThreadFactory implements ThreadFactory {
        private String name;

        public CustomThreadFactory(String name) {
            this.name = name;
        }

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, name);
        }
    }

}
