package com.manoj.upgradassignment.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.manoj.upgradassignment.R;

/**
 * Created by manoj on 21/06/16.
 */
public class SortDialog extends DialogFragment {
    private ISortDialogCallback sortDialogCallback;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISortDialogCallback) {
            sortDialogCallback = (ISortDialogCallback) activity;
        } else {
            throw new IllegalStateException("activity should implement ISortDialogCallback");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_sort)
                .setItems(R.array.sort_type, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                sortDialogCallback.onSortTypeSelected(getSortType(which));
                            }
                        }
                );
        // Create the AlertDialog object and return it
        return builder.create();
    }


    public SortType getSortType(int which) {
        return SortType.fromOrdinal(which);
    }

    public interface ISortDialogCallback {
        void onSortTypeSelected(SortType type);
    }
}
