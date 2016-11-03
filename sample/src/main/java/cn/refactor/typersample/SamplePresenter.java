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
        mView.setTextData("针对近日凤凰新闻对今日头条“流量劫持”的声明，今日头条以“诽谤”为由提起诉讼，认为凤凰新闻客户端侵犯了今日头条名誉，要求致歉并赔偿经济损失500万元。海淀法院日前受理了该案。");
        mView.startTyper();
    }
}
