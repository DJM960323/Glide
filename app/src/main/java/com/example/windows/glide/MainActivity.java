package com.example.windows.glide;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private ImageView internetImage;
    private ImageView localImage;
    //保存两个数组下标的变量
    public int i = 0;
    //保存网络图片的URL链接的字符串数组

    String[] urls = new String[]{
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1566881959279&di=81f7efae87ad753eb5c87b64854d410a&imgtype=0&src=http%3A%2F%2Fpic32.nipic.com%2F20130813%2F3347542_160503703000_2.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1566881959279&di=3cf353750e9a8d01f8695cd88660a703&imgtype=0&src=http%3A%2F%2Fpic25.nipic.com%2F20121117%2F9252150_165726249000_2.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1566881959279&di=0282a45490e261ea0ac4b616a2eea14a&imgtype=0&src=http%3A%2F%2Fgss0.baidu.com%2F-fo3dSag_xI4khGko9WTAnF6hhy%2Flvpics%2Fw%3D600%2Fsign%3D1350023d79899e51788e391472a5d990%2Fb21bb051f819861810d03e4448ed2e738ad4e65f.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1566881959279&di=e6d81364ed5c6673f989ecb2a5fc0f33&imgtype=0&src=http%3A%2F%2Fa.hiphotos.baidu.com%2Flvpics%2Fh%3D800%2Fsign%3D2d496375d739b60052ce02b7d9513526%2Fa6efce1b9d16fdfa97d6a678b68f8c5495ee7be9.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1566881959278&di=00a4a21640adbe0aa90c3489616a3d68&imgtype=0&src=http%3A%2F%2Fa.hiphotos.baidu.com%2Flvpics%2Fh%3D800%2Fsign%3D5a82402cd5ca7bcb627bca2f8e086b3f%2Fcaef76094b36acaf0651ef137ed98d1000e99caf.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1566881959278&di=9810df85e18c87561f9d57a8c50ccf0f&imgtype=0&src=http%3A%2F%2Fk.zol-img.com.cn%2Fdcbbs%2F22000%2Fa21999018_01000.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1566894990399&di=ef6c05218b4499c70ad3792cb953cecd&imgtype=0&src=http%3A%2F%2Fpic30.nipic.com%2F20130619%2F9885883_210838271000_2.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1566894990398&di=2d9952757331894a691e2d700fb29362&imgtype=0&src=http%3A%2F%2Fpic26.nipic.com%2F20121221%2F9252150_142515375000_2.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1566894990383&di=142def8b8b98ee919e035f3e27cfcc84&imgtype=0&src=http%3A%2F%2Fpic39.nipic.com%2F20140321%2F18063302_210604412116_2.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1566894990381&di=3b45dcf84167e3761d35dc2fbbf1e0af&imgtype=0&src=http%3A%2F%2Fpic44.nipic.com%2F20140727%2F18179070_152408117000_2.jpg"
    };

    //保存本地图片的数组
    int[] localPicture = new int[]{
            R.drawable.first, R.drawable.second,R.drawable.third,
            R.drawable.fourth,R.drawable.fifth,R.drawable.sixth,
            R.drawable.seventh,R.drawable.eighth,R.drawable.ninth,R.drawable.tenth
    };

    public Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            //设置case用于处理Message消息
            switch (msg.arg1){
                case 0:
                    //加载网络图片，load()方法里面的URL链接需为https协议
                    Glide.with(MainActivity.this)
                            .load(urls[msg.arg1])
                            //图片未加载完成时显示的图片，想想还是注释掉算了
//                            .placeholder(R.drawable.load)
                            .into(internetImage);
                    //同加载网络图片类似，只不过load()方法的参数为本地资源
                    Glide.with(MainActivity.this)
                            .load(localPicture[msg.arg2])
                            .into(localImage);
                    //重复调用loadPicture()方法，并把下标往后移动一位
                    loadPicture(urls,localPicture,msg.arg1 + 1);
                    break;
                case 1:
                    //通case0一样，后面的case都是相同的操作，只不过初始case跟结尾case特殊一点
                    Glide.with(MainActivity.this)
                            .load(urls[msg.arg1])
//                            .placeholder(R.drawable.load)
                            .into(internetImage);
                    Glide.with(MainActivity.this)
                            .load(localPicture[msg.arg2])
                            .into(localImage);
                    loadPicture(urls,localPicture,msg.arg1 + 1);
                    break;
                case 2:
                    Glide.with(MainActivity.this)
                            .load(urls[msg.arg1])
//                            .placeholder(R.drawable.load)
                            .into(internetImage);
                    Glide.with(MainActivity.this)
                            .load(localPicture[msg.arg2])
                            .into(localImage);
                    loadPicture(urls,localPicture,msg.arg1 + 1);
                    break;
                case 3:
                    Glide.with(MainActivity.this)
                            .load(urls[msg.arg1])
//                            .placeholder(R.drawable.load)
                            .into(internetImage);
                    Glide.with(MainActivity.this)
                            .load(localPicture[msg.arg2])
                            .into(localImage);
                    loadPicture(urls,localPicture,msg.arg1 + 1);
                    break;
                case 4:
                    Glide.with(MainActivity.this)
                            .load(urls[msg.arg1])
//                            .placeholder(R.drawable.load)
                            .into(internetImage);
                    Glide.with(MainActivity.this)
                            .load(localPicture[msg.arg2])
                            .into(localImage);
                    loadPicture(urls,localPicture,msg.arg1 + 1);
                    break;
                case 5:
                    Glide.with(MainActivity.this)
                            .load(urls[msg.arg1])
//                            .placeholder(R.drawable.load)
                            .into(internetImage);
                    Glide.with(MainActivity.this)
                            .load(localPicture[msg.arg2])
                            .into(localImage);
                    loadPicture(urls,localPicture,msg.arg1 + 1);
                    break;
                case 6:
                    Glide.with(MainActivity.this)
                            .load(urls[msg.arg1])
//                            .placeholder(R.drawable.load)
                            .into(internetImage);
                    Glide.with(MainActivity.this)
                            .load(localPicture[msg.arg2])
                            .into(localImage);
                    loadPicture(urls,localPicture,msg.arg1 + 1);
                    break;
                case 7:
                    Glide.with(MainActivity.this)
                            .load(urls[msg.arg1])
//                            .placeholder(R.drawable.load)
                            .into(internetImage);
                    Glide.with(MainActivity.this)
                            .load(localPicture[msg.arg2])
                            .into(localImage);
                    loadPicture(urls,localPicture,msg.arg1 + 1);
                    break;
                case 8:
                    Glide.with(MainActivity.this)
                            .load(urls[msg.arg1])
//                            .placeholder(R.drawable.load)
                            .into(internetImage);
                    Glide.with(MainActivity.this)
                            .load(localPicture[msg.arg2])
                            .into(localImage);
                    loadPicture(urls,localPicture,msg.arg1 + 1);
                    break;
                case 9:
                    Glide.with(MainActivity.this)
                            .load(urls[msg.arg1])
//                            .placeholder(R.drawable.load)
                            .into(internetImage);
                    Glide.with(MainActivity.this)
                            .load(localPicture[msg.arg2])
                            .into(localImage);
                    //因为两个数组的总长度为10，所以到最后一个元素的时候将下标置零以达到循环的目的
                    loadPicture(urls,localPicture,0);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        internetImage = (ImageView) findViewById(R.id.load_from_internet);
        localImage = (ImageView) findViewById(R.id.load_from_local);

        loadPicture(urls,localPicture,i);


    }

    //使用Handler异步消息收发，
    public void loadPicture(String[] url,int[] localPicture,int i) {
        Message message = new Message();
        //保存要加载的图片的URL链接
        message.obj = url[i];
        //保存数组下标，在handleMessage()方法中通过它来判断加载的图片
        message.arg1 = i;
        //保存本地图片的下标，同上
        message.arg2 = i;
        //设置每隔300毫秒发送一次message消息
        handler.sendMessageDelayed(message,300);
    }
}




