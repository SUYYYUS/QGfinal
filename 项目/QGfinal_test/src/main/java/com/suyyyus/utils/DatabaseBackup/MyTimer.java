package com.suyyyus.utils.DatabaseBackup;

import java.io.IOException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 进行数据备份
 */
public class MyTimer {

    private ScheduledThreadPoolExecutor executor ;

    private Runnable task;

    private boolean on;

    public MyTimer(){
        executor = new ScheduledThreadPoolExecutor(2);

        task = new Runnable() {
            @Override
            public void run(){
                try {
                    bakcup();
                    System.out.println(3456);
                } catch (IOException e) {
                    System.out.println(111);
                    e.printStackTrace();
                }
                System.out.println("heloo niko");
            }
        };
        //先为关闭状态
        on = false;
    }

    /**
     * 定时任务
     */
    public void start(){
        if(!on){
            executor.scheduleAtFixedRate(task,0,24, TimeUnit.HOURS);
            on = true;
        }
    }

    /**
     * 关闭定时任务
     */
    public void stop(){
        if(on){
            executor.shutdownNow();
            on = false;
        }
    }

    //数据备份函数
    public void bakcup() throws IOException {
        String command = "cmd /c " + "mysqldump -uroot -pcsy090944CSY db01>C:\\Users\\28937\\Desktop\\hhh.sql";

        Runtime.getRuntime().exec(command);
    }
}
