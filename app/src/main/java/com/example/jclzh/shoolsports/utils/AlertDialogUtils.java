package com.example.jclzh.shoolsports.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jclzh.shoolsports.R;

public class AlertDialogUtils {
    public void showDialog(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog dialog = builder.create();
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setButton(android.content.DialogInterface.BUTTON_NEUTRAL, "确定", new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(android.content.DialogInterface dialog, int which) {
                dialogInterface.NeutralListener(dialog, which, "");
            }
        });
        dialog.setButton(android.content.DialogInterface.BUTTON_NEGATIVE, "取消", new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(android.content.DialogInterface dialog, int which) {
                dialogInterface.NegativeListener(dialog, which);
            }
        });
        dialog.show();
    }

    public void showEditDialog(final Context context, String title) {
        final EditText editText = new EditText(context);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog dialog = builder.create();
        dialog.setTitle(title);
        dialog.setView(editText);
        dialog.setButton(android.content.DialogInterface.BUTTON_NEUTRAL, "确定", new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(android.content.DialogInterface dialog, int which) {
                String string = editText.getText().toString();
                dialogInterface.NeutralListener(dialog, which, string);
            }
        });
        dialog.setButton(android.content.DialogInterface.BUTTON_NEGATIVE, "取消", new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(android.content.DialogInterface dialog, int which) {
                dialogInterface.NegativeListener(dialog, which);
            }
        });
        dialog.show();
    }

    public void singleDialog(final Context context, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final String[] single_list = {"男","女"};
        builder.setTitle(title);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setSingleChoiceItems(single_list, 0, new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(android.content.DialogInterface dialog, int which) {
                String str = single_list[which];
                dialogInterface.NeutralListener(dialog,which,str);
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showViewDialog(Context context,String title,View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setIcon(R.mipmap.ic_launcher);
        AlertDialog dialog = builder.create();
        dialog.setView(view);
        dialog.show();
    }

    public interface DialogInterface {
        void NeutralListener(android.content.DialogInterface dialog, int which, String string);

        void NegativeListener(android.content.DialogInterface dialog, int which);
    }


    private DialogInterface dialogInterface;


    public DialogInterface getDialogInterface() {
        return dialogInterface;
    }

    public void setDialogInterface(DialogInterface dialogInterface) {
        this.dialogInterface = dialogInterface;
    }

}
