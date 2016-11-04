package cn.refactor.typersample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.refactor.typer.OnWriteTextChangedListener;
import cn.refactor.typer.TyperEditText;


/**
 * Create by andy (https://github.com/andyxialm)
 * Create time: 16/10/31 09:08
 * Description : Sample
 */
public class SampleFragment extends Fragment implements SampleContract.View, View.OnClickListener {

    private SampleContract.SamplePresenter mPresenter;
    private TyperEditText mTyperEditText;

    public static SampleFragment newInstance() {
        return new SampleFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sample, container, false);
        root.findViewById(R.id.btn_restart).setOnClickListener(this);
        root.findViewById(R.id.btn_start).setOnClickListener(this);

        root.findViewById(R.id.btn_stop).setOnClickListener(this);
        root.findViewById(R.id.btn_pause).setOnClickListener(this);
        root.findViewById(R.id.btn_resume).setOnClickListener(this);

        mTyperEditText = (TyperEditText) root.findViewById(R.id.typer_edit_text);
        mTyperEditText.setOnWriteTextChangedListener(new OnWriteTextChangedListener() {
            @Override
            public void onChanged(int index) {

            }

            @Override
            public void onCompleted() {

            }
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        // imitate http-request
        mTyperEditText.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.loadTextData();
            }
        }, 1000);
    }

    @Override
    public void setTextData(String textData) {
        mTyperEditText.setText(textData);
    }

    @Override
    public void startTyper() {
        mTyperEditText.start();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setPresenter(SampleContract.SamplePresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_restart:
                restartTyper();
                break;
            case R.id.btn_start:
                startTyper();
                break;
            case R.id.btn_stop:
                stopTyper();
                break;
            case R.id.btn_pause:
                pauseTyper();
                break;
            case R.id.btn_resume:
                resumeTyper();
                break;
        }
    }

    private void restartTyper() {
        mTyperEditText.restart();
    }

    private void stopTyper() {
        mTyperEditText.stop();
    }

    private void resumeTyper() {
        mTyperEditText.resume();
    }

    private void pauseTyper() {
        mTyperEditText.pause();
    }
}
