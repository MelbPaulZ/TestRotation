package au.com.smedia.testrotation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by puzhao on 29/8/17.
 */

public class MyNews extends Fragment {

    public static final String KEY = "TAG";
    public static final String TAG = "MYNEWS";

    public MyNews(){

    }

    public static MyNews newInstance(){
        MyNews myNews = new MyNews();
        Bundle bundle = new Bundle();
        bundle.putString(KEY, TAG);
        myNews.setArguments(bundle);
        return myNews;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_mynews, container, false);

        return view;
    }

}
