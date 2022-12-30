package de.fuh.michel.fachpraktikum_wi2022.view.definition;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * This Class is needed, because otherwise the onMeasure Method does not
 * work. The ListView would only display one item, even if there are
 * more in the List.
 */
public class DefinitionListView extends ListView {

    public DefinitionListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DefinitionListView(Context context) {
        super(context);
    }

    public DefinitionListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
