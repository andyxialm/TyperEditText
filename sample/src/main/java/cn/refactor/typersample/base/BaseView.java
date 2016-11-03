package cn.refactor.typersample.base;

/**
 * Created by andy (https://github.com/andyxialm)
 * Creation time: 16/11/2 18:24
 * Description  :
 */
public interface BaseView<T> {

    void setPresenter(T presenter);
}
