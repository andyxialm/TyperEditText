package cn.refactor.typersample;

/**
 * Created by andy (https://github.com/andyxialm)
 * Creation time: 16/11/2 18:32
 * Description  :
 */
public class SamplePresenter implements SampleContract.SamplePresenter {

    private SampleContract.View mView;

    public SamplePresenter(SampleContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void loadTextData() {
        mView.setTextData("华为Mate9搭载新一代华为麒麟960芯片，与上一代相比，八核CPU数据处理能力大幅提升，Mali G71八核GPU图形处理性能提升180%，支持最新的图形标准Vulkan。");
        mView.startTyper();
    }
}
