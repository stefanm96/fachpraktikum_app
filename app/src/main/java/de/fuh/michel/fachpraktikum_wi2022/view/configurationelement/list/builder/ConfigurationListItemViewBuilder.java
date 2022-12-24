package de.fuh.michel.fachpraktikum_wi2022.view.configurationelement.list.builder;

import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import de.fuh.michel.fachpraktikum_wi2022.model.ConfigurationElement;

public interface ConfigurationListItemViewBuilder {
    View buildListItemView(ViewGroup parent,
                           LayoutInflater inflater,
                           ConfigurationElement configurationElement,
                           int position);

    default void addDragHandle(View root, View dragHandle, int position) {
        dragHandle.setOnTouchListener((v, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("position", String.valueOf(position));
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(root);
                root.startDragAndDrop(data, shadowBuilder, root, 0);
            }

            v.performClick();
            return true;
        });
    }
}
