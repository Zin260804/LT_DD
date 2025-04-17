package com.example.btap5.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.btap5.MainActivity;
import com.example.btap5.R;
import com.example.btap5.model.NotesModel;
import com.example.btap5.sqlite.SqlLiteActivity;

import java.util.List;

public class NotesAdapter extends BaseAdapter {
    private SqlLiteActivity context;
    private int layout;
    private List<NotesModel> noteList;

    public NotesAdapter(SqlLiteActivity context, int layout, List<NotesModel> noteList) {
        this.context = context;
        this.layout = layout;
        this.noteList = noteList;
    }

    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Object getItem(int i) {
        return noteList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            viewHolder.textViewNote = (TextView) convertView.findViewById(R.id.textViewNameNote);
            viewHolder.imageViewDelete = (ImageView) convertView.findViewById(R.id.imageViewDelete);
            viewHolder.imageViewEdit = (ImageView) convertView.findViewById(R.id.imageViewEdit);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Gán dữ liệu cho ViewHolder
        NotesModel note = noteList.get(position);
        viewHolder.textViewNote.setText(note.getNameNote());
        //Läy giả trị
        final NotesModel notes = noteList.get(position);
        viewHolder.textViewNote.setText(notes.getNameNote());

        viewHolder. imageViewEdit.setOnClickListener(new View. OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Cap nhật " + notes.getNameNote(), Toast.LENGTH_SHORT).show();
                context.DialogCapNhatNotes(notes.getNameNote(), notes.getIdNote());
            }
        });

        viewHolder. imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogDelete(notes.getNameNote(),notes.getIdNote());
            }
        });
        return convertView;
    }

    private class ViewHolder {
        TextView textViewNote;
        ImageView imageViewEdit;
        ImageView imageViewDelete;
    }
}
