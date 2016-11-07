/**
 * Copyright 2016 andy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.refactor.typer;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;


/**
 * Create by andy (https://github.com/andyxialm)
 * Create time: 16/10/30 20:10
 * Description : Typewriter EditText
 */
public class TyperEditText extends EditText implements ITyperControl {

    private static final int INTERVAL_CHARACTER_WRITE   = 200;
    private static final int INTERVAL_PUNCTUATION_WRITE = 500;

    private int mCharacterWriteInterval;
    private int mPunctuationWriteInterval;
    private int mIndex;

    private boolean mWriting;
    private boolean mStop;
    private boolean mPause;

    private boolean mTouchable;
    private Editable mEditable;
    private OnWriteTextChangedListener mTextChangedListener;

    private RecycleWriteRunnable mRecycleWriteRunnable;

    public TyperEditText(Context context) {
        this(context, null);
    }

    public TyperEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TyperEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TyperEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return !mTouchable || super.onTouchEvent(event);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type == BufferType.NORMAL ? BufferType.EDITABLE : type);
        if (type != BufferType.NORMAL) {
            mEditable = getText();
            super.setText(null, BufferType.NORMAL);
        }
    }

    public void setOnWriteTextChangedListener(OnWriteTextChangedListener l) {
        mTextChangedListener = l;
    }

    public boolean isWriting() {
        return mWriting;
    }

    public boolean isStop() {
        return mStop;
    }

    public boolean isPause() {
        return mPause;
    }

    @Override
    public void restart() {
        start();
    }

    @Override
    public void start() {
        if (mEditable == null || mEditable.length() == 0) {
            return;
        }
        clearStatus();
        setWriteStatus(true);
        recycleWrite(mIndex);
    }

    @Override
    public void stop() {
        mStop = true;
        mIndex = 0;
        removeCallbacks(mRecycleWriteRunnable);
    }

    @Override
    public void resume() {
        if (!isStop() && isPause()) {
            mStop = false;
            mPause = false;
            recycleWrite(++ mIndex);
        }
    }

    @Override
    public void pause() {
        mPause = true;
        removeCallbacks(mRecycleWriteRunnable);
    }

    @Override
    protected void onDetachedFromWindow() {
        clearStatus();
        super.onDetachedFromWindow();
    }

    private void init(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.TyperEditText);
        try {
            mCharacterWriteInterval = ta.getInteger(R.styleable.TyperEditText_characterWriteInterval, INTERVAL_CHARACTER_WRITE);
            mPunctuationWriteInterval = ta.getInteger(R.styleable.TyperEditText_punctuationWriteInterval, INTERVAL_PUNCTUATION_WRITE);
            mTouchable = ta.getBoolean(R.styleable.TyperEditText_touchable, false);
        } finally {
            ta.recycle();
        }
    }

    private void clearStatus() {
        mStop = false;
        mPause = false;
        mIndex = 0;
        setText(null, BufferType.NORMAL);
        removeCallbacks(mRecycleWriteRunnable);
    }

    private void setWriteStatus(boolean writing) {
        mWriting = writing;
    }

    private void recycleWrite(int index) {
        if (mEditable == null || mEditable.length() == 0 || index == mEditable.length()) {
            setWriteStatus(false);
            if (mTextChangedListener != null) {
                mTextChangedListener.onCompleted();
            }
            return;
        }

        if (mPause || mStop) {
            return;
        }

        if (mRecycleWriteRunnable == null) {
            mRecycleWriteRunnable = new RecycleWriteRunnable();
        }

        char curCharacter = mEditable.charAt(index);
        append(String.valueOf(curCharacter));
        setSelection(index + 1);

        int interval = isChinesePunctuation(curCharacter) ? mPunctuationWriteInterval : mCharacterWriteInterval;
        postDelayed(mRecycleWriteRunnable, interval);
    }

    private boolean isChinesePunctuation(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS;
    }

    private class RecycleWriteRunnable implements Runnable {

        @Override
        public void run() {
            if (mTextChangedListener != null) {
                mTextChangedListener.onChanged(mIndex);
            }
            recycleWrite(++ mIndex);
        }
    }
}
