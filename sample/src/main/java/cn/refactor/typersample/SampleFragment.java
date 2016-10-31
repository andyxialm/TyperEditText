package cn.refactor.typersample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.refactor.typer.TyperEditText;


/**
 * Create by andy (https://github.com/andyxialm)
 * Create time: 16/10/31 09:08
 * Description : Sample
 */
public class SampleFragment extends Fragment {

    public static SampleFragment newInstance() {
        return new SampleFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sample, container, false);
        TyperEditText typerEditText = (TyperEditText) root.findViewById(R.id.typer_edit_text);
        typerEditText.start();
        return root;
    }
}
