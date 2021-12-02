package edu.illinois.cs465.pixelbyte;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;

import edu.illinois.cs465.pixelbyte.ClassList.FileListener;
import edu.illinois.cs465.pixelbyte.ClassStructures.ClassData;
import edu.illinois.cs465.pixelbyte.ClassList.ClassListAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    BottomSheetDialogFragment openDialog;
    private String currentSemester;
    private List<ClassData> classes; //each elem = class name, letter, number
    private ClassListAdapter adapter;
    List<FileListener> listeners_;
    ClassData inProgress_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView createClassButton = (CardView) findViewById(R.id.create_class);
        createClassButton.setOnClickListener(this);

        //stuff to grab from other screens
        currentSemester = "Spring 2022";

        generateClasses();
        if (classes.size() == 0) {
            classes = ClassData.createSampleList(this);
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
            classes = ClassData.createSampleList(this);
        }
    }

    public void updateClass(int index) {
        readClasses();
    }

    private void readClasses() {
        classes = new ArrayList<>();

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
                    classes.add(cd);
                }
            }

        } catch (Exception e) {}
    }

    private void createClassList() {
        // Create adapter to interpret data
        getSupportActionBar().setTitle(currentSemester);

        //setup listview
        ListView classList = (ListView) findViewById(R.id.classlist);
        TextView noClasses = (TextView) this.findViewById(R.id.noclasses);

        if (classes.size() == 0) {
            noClasses.setVisibility(View.VISIBLE);
            classList.setVisibility(View.INVISIBLE);
        } else {
            classList.setVisibility(View.VISIBLE);
            noClasses.setVisibility(View.INVISIBLE);

            adapter = new ClassListAdapter(this, classes);
            classList.setAdapter(adapter);
        }
    }

    public void openDialog(BottomSheetCodes code, String bottomSheetName) {
        if (openDialog != null && openDialog.isVisible()) {
            openDialog.onDestroyView();
        }

        switch (code) {
            case CreateClass:
                openDialog = new CreateClass();
                break;
            case FindTemplate:
                openDialog = new FindTemplate();
                break;
            case NewTemplatePreview:
                openDialog = new CreateNewTemplate();
                break;
            case AddCategory:
                openDialog = new AddCategory();
                break;
            case PreviewTemplate:
                openDialog = new PreviewTemplate(bottomSheetName);
                break;
        }

        openDialog.show(getSupportFragmentManager(), bottomSheetName);
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
        classes.add(inProgress_);
        adapter.notifyDataSetChanged();
    }
}