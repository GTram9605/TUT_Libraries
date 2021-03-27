package za.ac.nplinnovations.tutlibraries.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import za.ac.nplinnovations.tutlibraries.R;
import za.ac.nplinnovations.tutlibraries.entities.Message;

public class AdapterMessages extends ArrayAdapter {
    private Context mContext;
    private List<Message> mList;

    public AdapterMessages(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        mContext = context;
        mList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        Message m = mList.get(position);

        if(m.isIs_from_student()){
            if (view == null)
                view = LayoutInflater.from(mContext).inflate(R.layout.layout_message_sent, null);
        }else{

            if (view == null)
                view = LayoutInflater.from(mContext).inflate(R.layout.layout_message_received, null);
        }

        TextView timestamp = (TextView) view.findViewById(R.id.tvTime);
        TextView textMessage = (TextView) view.findViewById(R.id.txtMessage);

        timestamp.setText(m.getTimestamp());
        textMessage.setText(m.getMessage());

        return view;
    }
}
