package cn.refactor.typer;

/**
 * Create by andy (https://github.com/andyxialm)
 * Create time: 16/10/30 20:15
 * Description :
 */
public interface ITyperControl {
    /**
     * start write
     */
    void start();

    /**
     * stop write
     */
    void stop();

    /**
     * resume to write
     */
    void resume();

    /**
     * pause
     */
    void pause();
}
