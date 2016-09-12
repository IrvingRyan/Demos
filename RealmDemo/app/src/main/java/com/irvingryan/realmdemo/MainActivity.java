package com.irvingryan.realmdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.irvingryan.realmdemo.beans.User;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textView;
    private Realm realm;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initParams();
    }

    private void initParams() {
        RealmConfiguration configuration = new RealmConfiguration.Builder(this)
                .name("1.realm")//配置名字
                .encryptionKey(new byte[64])//加密用字段,不是64位会报错
                .schemaVersion(2)//版本号
//                .setModules(xxxx)//不懂
//                .migration(new rea)//不懂
//                .inMemory()//设置后会放在缓存中
                .build();
        realm = Realm.getInstance(configuration);
    }

    private void initView() {
        findViewById(R.id.save).setOnClickListener(this);
        findViewById(R.id.get).setOnClickListener(this);
        findViewById(R.id.delete).setOnClickListener(this);
        findViewById(R.id.update).setOnClickListener(this);
        textView = (TextView)findViewById(R.id.text);
    }

    private void insert() {
        realm.beginTransaction();
        User user = realm.createObject(User.class);
        user.setSessionId(++i);
        user.setAge(20);
        user.setName("Irving Ryan");
        realm.commitTransaction();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:
                insert();
                break;
            case R.id.get:
                find();
                break;
            case R.id.update:
                update();
                break;
            case R.id.delete:
                delete();
                break;
        }
    }

    private void delete() {
        realm.beginTransaction();
        realm.where(User.class).findAll().deleteAllFromRealm();
        realm.commitTransaction();//提交事务

    }

    /**
     * 更新方法 ，没有主键无法更新
     */
    private void update() {
        realm.beginTransaction();
        //1.第一种方法 数据不存在会执行插入操作
//        User b = new User();//新建实例
//        b.setSessionId(1);//user类新加的int属性，用@PrimaryKey注解作为主键
//        b.setAge(25);
//        b.setName("史记");
//        realm.copyToRealmOrUpdate(b);//修改操作
        //2.方法二 realm推荐使用的方法 先查出来再改
        User first = realm.where(User.class).findFirst();
        first.setAge(25);
        first.setName("史记");
        realm.commitTransaction();//提交事务
    }

    private void find() {
        RealmResults<User> list = realm.where(User.class).findAll();
        StringBuilder builder = new StringBuilder();
        for (int i=0;i<list.size();i++){
            User user = list.get(i);
            builder.append(user.getName()+" "+user.getAge()+"\n");
        }
        textView.setText(builder.toString());
    }
}
