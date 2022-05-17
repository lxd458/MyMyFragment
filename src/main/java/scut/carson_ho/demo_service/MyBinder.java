package scut.carson_ho.demo_service;

import android.os.Binder;
//新建一个子类继承自Binder类
/* 【 Binder机制实现: 用于Service类和Activity 】
 另外一种方法：可以把此类放在MyService类里面，作为第二个类
 但现在是单独分开调用;
 当调用【服务绑定】的时候，运用了ServiceConnection实例对象的 onServiceConnected（）服务关联方法
 */
public class MyBinder extends Binder {
    //声明一个公共调用方法 service_connect_Activity()
    public void service_connect_Activity() {
        System.out.println("Service关联了Activity,并在Activity执行了Service的方法");

    }
}
