package edu.illinois.cs465.pixelbyte;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import edu.illinois.cs465.pixelbyte.AssignmentList.AssignmentListAdapter;
import edu.illinois.cs465.pixelbyte.ClassStructures.TemplateCategory;

public class ViewCategory extends AppCompatActivity {
    TemplateCategory category_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_category);

        category_ = TemplateCategory.extract(savedInstanceState, 0);

        setTitle(category_.name_);

        TextView percentTag = (TextView) findViewById(R.id.numgrade);
        percentTag.setText(category_.getGrade());

        ListView assignmentList = (ListView) findViewById(R.id.assignment_list);
        AssignmentListAdapter adapter = new AssignmentListAdapter(this, category_.enteredAssignments_);
        assignmentList.setAdapter(adapter);
    }
}