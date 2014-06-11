package com.collosteam.ListAndGridViewDragAnimationsDemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        findViewById(R.id.button_list).setOnClickListener(this);
        findViewById(R.id.button_grid).setOnClickListener(this);
        findViewById(R.id.button_grid_items).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.button_list:
                startActivity(new Intent(this, ListViewDraggingAnimationActivity.class));
                return;
            case R.id.button_grid:
                startActivity(new Intent(this, GridViewDraggingAnimationActivity.class));
                return;
            case R.id.button_grid_items:
                startActivity(new Intent(this, GridViewItemsDraggingAnimationActivity.class));
                return;
            default:
                throw new RuntimeException("Unknown view id: " + id);
        }
    }
}
