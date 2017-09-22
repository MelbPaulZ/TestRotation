package au.com.smedia.testrotation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.smedia.library.SmediaSettings;
import com.smedia.library.model.SettingsInfo;

/**
 * Created by puzhao on 29/8/17.
 */

public class Settings extends Fragment implements CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {



    public static final String KEY = "TAG";
    public static final String TAG = "SETTINGS";

    SettingsInfo settingsInfo;
    Switch highlightSwitch, wifiOnlySwitch, autoDownloadSwitch;
    Spinner autoDelete;

    public Settings(){

    }

    public static Settings newInstance(){
        Settings myNews = new Settings();
        Bundle bundle = new Bundle();
        bundle.putString(KEY, TAG);
        myNews.setArguments(bundle);
        return myNews;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        TextView highlightText = (TextView) view.findViewById(R.id.highlight_text);
        TextView highlightDesc = (TextView) view.findViewById(R.id.highlight_desc);
        TextView wifiOnlyText = (TextView) view.findViewById(R.id.wifi_text);
        TextView wifiOnlyDesc = (TextView) view.findViewById(R.id.wifi_desc);
        TextView autoDownloadText = (TextView) view.findViewById(R.id.autodownload_text);
        TextView autoDownloadDesc = (TextView) view.findViewById(R.id.autodownload_desc);
        TextView autoDeleteText = (TextView) view.findViewById(R.id.delete_text);
        TextView autoDeleteDesc = (TextView) view.findViewById(R.id.delete_desc);

        highlightSwitch = (Switch) view.findViewById(R.id.highlight_switch);
        wifiOnlySwitch = (Switch) view.findViewById(R.id.wifionly_switch);
        autoDownloadSwitch = (Switch) view.findViewById(R.id.autodownload_switch);
        autoDelete = (Spinner) view.findViewById(R.id.auto_delete_spinner);

        //Setup Settings
        SmediaSettings settings = new SmediaSettings(getContext());
        settingsInfo = settings.getSettings();

        //add the listener
        settings.setOnSettingsChangeListener(new SmediaSettings.OnSettingsChangeListener() {
            @Override
            public void settingsChanged(boolean changed) {
                if (changed) {
                    //This will notify your class that the Settings is changed;
                    Log.v("SETTINGS","applied");

                }
            }
        });


        //Get Settings Title and Descriptions
        highlightText.setText(settingsInfo.getEnableHighlightText());
        highlightDesc.setText(settingsInfo.getEnableHighlightTextDesc());
        wifiOnlyText.setText(settingsInfo.getAllowOnWifiOnlyText());
        wifiOnlyDesc.setText(settingsInfo.getAllowOnWifiOnlyTextDesc());
        autoDownloadText.setText(settingsInfo.getEnableAutoDownloadText());
        autoDownloadDesc.setText(settingsInfo.getEnableAutoDownloadTextDesc());
        autoDeleteText.setText(settingsInfo.getAutoDeleteText());
        autoDeleteDesc.setText(settingsInfo.getAutoDeleteTextDesc());


        highlightSwitch.setChecked(settingsInfo.getHighlightSwitch());
        wifiOnlySwitch.setChecked(settingsInfo.getWifiOnlySwitch());
        autoDownloadSwitch.setChecked(settingsInfo.getAutoDownloadSwitch());

        if (settingsInfo.getAutoDeleteItem() == settingsInfo.THREEDAYS) {
            autoDelete.setSelection(0);
        } else if (settingsInfo.getAutoDeleteItem() == settingsInfo.SEVENDAYS) {
            autoDelete.setSelection(1);
        } else if (settingsInfo.getAutoDeleteItem() == settingsInfo.FOURTEENDAYS) {
            autoDelete.setSelection(2);
        } else if (settingsInfo.getAutoDeleteItem() == settingsInfo.TWENTYONEDAYS) {
            autoDelete.setSelection(3);
        }

        highlightSwitch.setOnCheckedChangeListener(this);
        wifiOnlySwitch.setOnCheckedChangeListener(this);
        autoDownloadSwitch.setOnCheckedChangeListener(this);
        autoDelete.setOnItemSelectedListener(this);

        return view;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == highlightSwitch){
            settingsInfo.setEnableHighlightSwitch(isChecked);
            //Don't forget to notify Observer to update the SDK
            settingsInfo.stateChanged();
        } else if (buttonView == wifiOnlySwitch){
            settingsInfo.setAllowWifiOnlySwitch(isChecked);
            //Don't forget to notify Observer to update the SDK
            settingsInfo.stateChanged();
        } else if (buttonView == autoDownloadSwitch){
            settingsInfo.setAutoDownloadSwitch(isChecked);
            //Don't forget to notify Observer to update the SDK
            settingsInfo.stateChanged();
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                settingsInfo.setAutoDeleteItem(SettingsInfo.THREEDAYS);
                settingsInfo.stateChanged();
                break;
            case 1:
                settingsInfo.setAutoDeleteItem(SettingsInfo.SEVENDAYS);
                settingsInfo.stateChanged();
                break;
            case 2:
                settingsInfo.setAutoDeleteItem(SettingsInfo.FOURTEENDAYS);
                settingsInfo.stateChanged();
                break;
            case 3:
                settingsInfo.setAutoDeleteItem(SettingsInfo.TWENTYONEDAYS);
                settingsInfo.stateChanged();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

