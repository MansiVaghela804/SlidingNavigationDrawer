package www.smartandroidcourse.com.slidingnavigationdrawer;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;


import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;

import www.smartandroidcourse.com.slidingnavigationdrawer.Menu.DrawerAdapter;
import www.smartandroidcourse.com.slidingnavigationdrawer.Menu.DrawerItem;
import www.smartandroidcourse.com.slidingnavigationdrawer.Menu.SimpleItem;
import www.smartandroidcourse.com.slidingnavigationdrawer.Menu.SpaceItem;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener,DrawerAdapter.OnItemSelectedListener{

    private SlidingRootNav slidingRootNav;
    private static final int POS_DASHBOARD = 0;
    private static final int POS_ACCOUNT = 1;
    private static final int POS_MESSAGES = 2;
    private static final int POS_CART = 3;
    private static final int POS_LOGOUT = 5;

    private String[] screenTitles;
    private Drawable[] screenIcons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //NAVIGATION


        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(true)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter drawadapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_DASHBOARD).setChecked(true),
                createItemFor(POS_ACCOUNT),
                createItemFor(POS_MESSAGES),
                createItemFor(POS_CART),
                new SpaceItem(48),
                createItemFor(POS_LOGOUT)));
        drawadapter.setListener(this);

        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(drawadapter);

        drawadapter.setSelected(POS_DASHBOARD);


        //BUTTON


    }


    @Override
    public void onItemSelected(int position) {
        if (position == POS_LOGOUT) {
            finish();
        }
        if (position == POS_ACCOUNT

                ) {
            //     Intent i =new Intent(getApplication(), About.class);
            //    startActivity(i);
        }

        if (position == POS_CART

                ) {
            //   Intent i =new Intent(getApplication(), AdvertiseUs.class);
            //  startActivity(i);
        }

        if (position == POS_MESSAGES

                ) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT," Click to download Colors Soda app from wwww. ");
            sendIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"G E E N  B O X");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        }

        slidingRootNav.closeMenu();
        // Fragment selectedScreen = CenteredTextFragment.createFor(screenTitles[position]);
        // showFragment(selectedScreen);
    }



    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.textColorSecondary))
                .withTextTint(color(R.color.textColorSecondary))
                .withSelectedIconTint(color(R.color.navfun))
                .withSelectedTextTint(color(R.color.navfun));
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }
    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }
    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}


