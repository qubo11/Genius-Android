package net.qiujuer.sample.genius;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import net.qiujuer.genius.ui.drawable.RipAnimDrawable;
import net.qiujuer.genius.ui.widget.CheckBox;
import net.qiujuer.genius.ui.widget.FloatActionButton;
import net.qiujuer.sample.genius.drawable.AddLineShape;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, Toolbar.OnMenuItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initToolbar();
        initFloatActionButton();
        initCheckBox();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.action_add) {
            Toast.makeText(v.getContext(), "FlaotActionButton OnClick.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_blur) {
            Intent intent = new Intent(MainActivity.this, BlurActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_kit) {
            Intent intent = new Intent(MainActivity.this, KitActivity.class);
            startActivity(intent);
        }

        return true;
    }

    private void initToolbar() {
        // ToolBar
        RipAnimDrawable ripAnim = new RipAnimDrawable();
        ripAnim.setColor(getResources().getColor(R.color.cyan_600));
        ripAnim.setFluCount(0, 0, 0, 36);

        Toolbar toolbar = (Toolbar) findViewById(R.id.title);
        toolbar.setBackgroundDrawable(ripAnim);
        toolbar.setTitle(getTitle());
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(this);
    }

    private void initFloatActionButton() {
        final float density = getResources().getDisplayMetrics().density;
        FloatActionButton addButton = (FloatActionButton) findViewById(R.id.action_add);
        AddLineShape lineShape = new AddLineShape();
        ShapeDrawable drawable = new ShapeDrawable(lineShape);
        Paint paint = drawable.getPaint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(0xc0ffffff);
        paint.setStrokeWidth(2 * density);
        drawable.setIntrinsicWidth(100);
        drawable.setIntrinsicHeight(100);
        addButton.setImageDrawable(drawable);
        addButton.setOnClickListener(this);
    }

    private void initCheckBox() {
        final CheckBox none_a = (CheckBox) findViewById(R.id.checkbox_none_a);
        final CheckBox none_b = (CheckBox) findViewById(R.id.checkbox_none_b);
        final CheckBox custom_a = (CheckBox) findViewById(R.id.checkbox_custom_a);
        final CheckBox custom_b = (CheckBox) findViewById(R.id.checkbox_custom_b);
        none_a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                none_b.setEnabled(isChecked);
            }
        });

        custom_a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                custom_b.setEnabled(isChecked);
            }
        });
    }
}
