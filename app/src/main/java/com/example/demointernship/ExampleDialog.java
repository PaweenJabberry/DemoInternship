package com.example.demointernship;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class ExampleDialog extends AppCompatDialogFragment {
    private EditText editTextTitle;
    private EditText editTextMessage;
    private EditText editTextUrl;
    private ExampleDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view)
                .setTitle("New Item")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String title = editTextTitle.getText().toString();
                        String message = editTextMessage.getText().toString();
                        String url = editTextUrl.getText().toString();
                        listener.applyTexts(title,message,url);
                    }
                });
        editTextTitle = view.findViewById(R.id.edit_title);
        editTextMessage = view.findViewById(R.id.edit_message);
        editTextUrl = view.findViewById(R.id.edit_url);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() +
                    "Must implement ExampleDialogListener");
        }
    }

    public interface ExampleDialogListener{
        void applyTexts(String title, String message, String url);
    }
}
