package edu.illinois.cs465.pixelbyte;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;

import edu.illinois.cs465.pixelbyte.ClassList.FileListener;
import edu.illinois.cs465.pixelbyte.ClassStructures.ClassData;
import edu.illinois.cs465.pixelbyte.ClassList.ClassListAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    BottomSheetDialogFragment openDialog_;
    private String currentSemester_;
    private List<ClassData> classes_; //each elem = class name, letter, number
    private ClassListAdapter adapter_;
    List<FileListener> listeners_;
    ClassData inProgress_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView createClassButton = (CardView) findViewById(R.id.create_class);
        createClassButton.setOnClickListener(this);

        //stuff to grab from other screens
        currentSemester_ = "Spring 2022";

        generateClasses();
        if (classes_.size() == 0) {
            classes_ = ClassData.createSampleList(this);
        }
        createFileListeners();

        createClassList();
    }

    @Override
    protected void onStart() {
        super.onStart();
        generateClasses();
    }

    private void createFileListeners() {
        listeners_ = new ArrayList<>();
        File folder = getFilesDir();
        File files[] = folder.listFiles();

        for (File file : files) {
            FileListener fl = new FileListener(file, this);
            fl.startWatching();
            listeners_.add(fl);
        }
    }

    private void generateClasses() {
        try {
            readClasses();
        } catch (Exception e) {
            classes_ = ClassData.createSampleList(this);
        }
    }

    public void updateClass(int index) {
        readClasses();
    }

    private void readClasses() {
        classes_ = new ArrayList<>();

        try {
            File folder = getFilesDir();
            File[] files = folder.listFiles();

            for (File file : files) {
                if (file.getName().startsWith("class-")) {
                    String content = "";
                    Scanner reader = new Scanner(file);
                    while (reader.hasNextLine()) {
                        content += reader.nextLine();
                    }

                    Gson gson = new Gson();
                    ClassData cd = gson.fromJson(content, ClassData.class);
                    classes_.add(cd);
                }
            }

        } catch (Exception e) {}
    }

    private void createClassList() {
        // Create adapter to interpret data
        getSupportActionBar().setTitle(currentSemester_);

        //setup listview
        ListView classList = (ListView) findViewById(R.id.classlist);
        TextView noClasses = (TextView) this.findViewById(R.id.noclasses);

        if (classes_.size() == 0) {
            noClasses.setVisibility(View.VISIBLE);
            classList.setVisibility(View.INVISIBLE);
        } else {
            classList.setVisibility(View.VISIBLE);
            noClasses.setVisibility(View.INVISIBLE);

            adapter_ = new ClassListAdapter(this, classes_);
            classList.setAdapter(adapter_);
        }
    }

    public void openDialog(BottomSheetCodes code, String bottomSheetName) {
        if (openDialog_ != null && openDialog_.isVisible()) {
            openDialog_.onDestroyView();
        }

        switch (code) {
            case CreateClass:
                openDialog_ = new CreateClass();
                break;
            case FindTemplate:
                openDialog_ = new FindTemplate();
                break;
            case NewTemplatePreview:
                openDialog_ = new CreateNewTemplate();
                break;
            case AddCategory:
                openDialog_ = new AddCategory();
                break;
            case PreviewTemplate:
                openDialog_ = new PreviewTemplate(bottomSheetName);
                break;
        }

        openDialog_.show(getSupportFragmentManager(), bottomSheetName);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.create_class) {
            openDialog(BottomSheetCodes.CreateClass, "Create Class");
            return;
        }
    }

    public void startNewClass(String name, int color, String department) {
        inProgress_ = new ClassData(name, color, department);
    }

    public void finishTemplate() {
        inProgress_.saveData(this);
        classes_.add(inProgress_);
        adapter_.notifyDataSetChanged();
    }

    public void openTutoringDialog(String department) {
        DialogFragment df = new TutoringInformationFragment(department);
        df.show(getSupportFragmentManager(), "Tutoring Name");
    }
}