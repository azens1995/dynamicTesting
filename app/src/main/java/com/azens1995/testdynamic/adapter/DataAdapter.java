package com.azens1995.testdynamic.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.azens1995.testdynamic.R;
import com.azens1995.testdynamic.model.Data;

import org.w3c.dom.Text;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {


    private Context context;
    private List<Data> data;
    private int optionSize;

    public DataAdapter(Context context, List<Data> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.question.setText(data.get(i).getDescription());
        if (data.get(i).getType().equalsIgnoreCase("text")){
            viewHolder.enterAnswer.setVisibility(View.VISIBLE);

        }else if (data.get(i).getType().equalsIgnoreCase("radiobutton")){
            viewHolder.radioGroup.setVisibility(View.VISIBLE);
            for (int item = 0; item <= data.get(i).getType().length(); item++){
                RadioButton button = new RadioButton(context);
                button.setId(item + 1);
                button.setText(data.get(i).getOptions().get(item));
                viewHolder.radioGroup.addView(button);
            }
        }else if (data.get(i).getType().equalsIgnoreCase("select")){
            viewHolder.checkBox.setVisibility(View.VISIBLE);
            for (int item = 0; item <= data.get(i).getType().length(); item++){
                CheckBox checkBox = new CheckBox(context);
                checkBox.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) context);
                checkBox.setId(item);
                checkBox.setText(data.get(i).getOptions().get(item));

            }

        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView question;
        private EditText enterAnswer;
        private RadioGroup radioGroup;
        private CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            question = (TextView) itemView.findViewById(R.id.question);
            enterAnswer = (EditText) itemView.findViewById(R.id.answerText);
            radioGroup = (RadioGroup) itemView.findViewById(R.id.radiogroup);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        }
    }
}
