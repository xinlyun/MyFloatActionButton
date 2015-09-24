package com.lin.mfb;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.lin.myfloatactionbtn.IconClickListener;
import com.lin.myfloatactionbtn.MyFloatActionButton;
import com.lin.myfloatactionbtn.MyFloatActionMenu;


public class MainActivity extends Activity {
    MyFloatActionMenu menu1,menu2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        menu1 = (MyFloatActionMenu) findViewById(R.id.id_mfam1);
        menu2 = (MyFloatActionMenu) findViewById(R.id.id_mfam2);

        menu1.setOnIconClickListener(new IconClickListener() {
            @Override
            public void IconCLick(MyFloatActionButton view, int no) {
                Toast.makeText(MainActivity.this,"left_top:"+no,Toast.LENGTH_SHORT).show();
            }
        });
        menu2.setOnIconClickListener(new IconClickListener() {
            @Override
            public void IconCLick(MyFloatActionButton view, int no) {
                Toast.makeText(MainActivity.this,"right_bottom:"+no,Toast.LENGTH_SHORT).show();
            }
        });
    }


}
