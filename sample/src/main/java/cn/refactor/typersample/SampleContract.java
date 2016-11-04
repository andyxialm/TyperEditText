package cn.refactor.typersample;

import cn.refactor.typersample.base.BasePresenter;
import cn.refactor.typersample.base.BaseView;

/**
 * Created by andy (https://github.com/andyxialm)
 * Creation time: 16/11/2 18:18
 * Description  : Sample contract
 */
public interface SampleContract {

    interface View extends BaseView<SamplePresenter> {

        void setTextData(String text);

        void startTyper();

        boolean isActive();
    }

    interface SamplePresenter extends BasePresenter {

        /**
         * request get text data
         */
        void loadTextData();
    }
}
