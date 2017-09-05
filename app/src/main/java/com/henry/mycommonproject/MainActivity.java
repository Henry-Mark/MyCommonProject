package com.henry.mycommonproject;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.henry.commlibrary.activity.BaseLogActivity;
import com.henry.commlibrary.fragment.dialog.base.BaseMultiChoiceDialogFragment;
import com.henry.mycommonproject.httpTest.LoginRequeset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseLogActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                testHttp();
//                showDialoglist();
//                startActivity(new Intent(MainActivity.this, ImgActivity.class));
                showPopupWindow();
            }
        });
    }

    private void showPopupWindow() {
        TestPopupWindow window = new TestPopupWindow(this);
        window.showAtLocation(findViewById(R.id.activity_main), Gravity.BOTTOM | Gravity
                .CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
    }


    private void testHttp() {
        LoginRequeset requeset = new LoginRequeset(this, "加载中...", false);
        Map map = new HashMap();
        map.put("phoneNumber", "12345678910");
        map.put("password", "111111");
        map.put("serial", "12345678910");
        requeset.start(map);
    }

    private void showDialoglist() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("list" + i);
        }

        MultiChoiceDialogFragment dialogFragment = new MultiChoiceDialogFragment(null, list, "列表");
        dialogFragment.addMultiChoiceListener(new BaseMultiChoiceDialogFragment.OnMultiChoiceListener() {
            @Override
            public void onMultiSelected(View view, List list) {
                Toast.makeText(getApplicationContext(), list.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        dialogFragment.show(getSupportFragmentManager(), "");
    }
}
