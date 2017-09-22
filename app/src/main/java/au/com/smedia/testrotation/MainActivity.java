package au.com.smedia.testrotation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.paul.zhao.activity.SmediaAppCompatActivity;
import com.smedia.library.fragment.FragmentHandler;
import com.smedia.library.fragment.NewsStand;
import com.smedia.library.menu.MenuHandler;


public class MainActivity extends SmediaAppCompatActivity implements MenuHandler.OnMenuItemListener{

    private ViewPager viewPager;
    private FragmentManager fragmentManager;
    private MainPagerAdapter adapter;
    private MenuHandler navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        fragmentManager = getSupportFragmentManager();

        //Select your Viewpager Adapter
        adapter = new MainPagerAdapter(fragmentManager);
        viewPager.setAdapter(adapter);

        //Smedia Bottom Navbar
        navigation = new MenuHandler(this);
        navigation.setViewPager(viewPager);
        navigation.setOnMenuItemListener(this);
        viewPager.setCurrentItem(0);

        /*if (savedInstanceState == null) {
            //Select SMedia SDK
            FragmentHandler.SDK = FragmentHandler.HERALDSUN;
            //FragmentHandler.SDK = FragmentHandler.DAILYTELEGRAPH;
            //FragmentHandler.SDK = FragmentHandler.COURIERMAIL;
            //FragmentHandler.SDK = FragmentHandler.ADVERTISER;
            Fragment NewsstandFragment = NewsStand.newInstance();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.newsstand, NewsstandFragment).commit();
        }*/
    }

    @Override
    public void setPage(int page) {
        switch (page){
            case 0:
                viewPager.setCurrentItem(0);
                break;
            case 1:
                viewPager.setCurrentItem(1);
                break;
            case 2:
                viewPager.setCurrentItem(2);
            default:
                break;
        }
    }


    public class MainPagerAdapter extends FragmentPagerAdapter {

        private final int pages = 3;
        public MainPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return MyNews.newInstance();
                case 1:
                    //Select SMedia SDK
                    //FragmentHandler.SDK = FragmentHandler.HERALDSUN;
                    //FragmentHandler.SDK = FragmentHandler.DAILYTELEGRAPH;
                    FragmentHandler.SDK = FragmentHandler.COURIERMAIL;
//                    FragmentHandler.SDK = FragmentHandler.ADVERTISER;
                    return NewsStand.newInstance();
                case 2:
                    return Settings.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return pages;
        }


    }
}
