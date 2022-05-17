package scut.carson_ho.demo_service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;

import android.os.IBinder;


import androidx.annotation.Nullable;

/*
 * 创建MyService类 ; 需继承Service类
 * 生命周期：
 *    开启服务： onCreate()  +  onStartCommand()
 *    关闭服务：  onDestroy()
 *
 * 服务绑定 ： 执行了onCreat()  + 执行了onBind()
 *  IBinder onBind(Intent intent)
 *
 * 服务解绑 ： 执行了onUnbind() + 执行了onDestory()
 *  Boolean  onUnbind(Intent intent)
 *
 */
public class MyService extends Service {
      //1.注入MyBinder类
    private MyBinder mBinder = new MyBinder();
    //2.重写onCreate()
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("执行了onCreat()");
       /* 【后台服务 变成 前台Service】
         在onCreate()方法里面 PendingIntent.getActivity()
        */
        //2.1构建"点击通知后 打开MainActivity"的Intent对象
        Intent notificationIntent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0);

        //2.2 Notification.Builder:  构建Notification通知对象
        Notification.Builder builer = new Notification.Builder(this);
        builer.setContentTitle("前台服务通知的标题");//设置通知的标题
        builer.setContentText("前台服务通知的内容");//设置通知的内容
        builer.setSmallIcon(R.mipmap.ic_launcher);//设置通知的图标
        builer.setContentIntent(pendingIntent);//设置点击通知后的操作

        Notification notification = builer.getNotification();//将Builder对象转变成普通的notification
        startForeground(1, notification);//让Service变成前台Service,并在系统的状态栏显示出来

    }
    //3.重写onStartCommand()
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("执行了onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    //4.重写onDestroy()
    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("执行了onDestory()");
    }

    @Nullable
    @Override  //5.
    public IBinder onBind(Intent intent) {
        System.out.println("执行了onBind()");
        //返回实例，而不是null
        return mBinder;
    }
    //6.
    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("执行了onUnbind()");
        return super.onUnbind(intent);
    }




}
