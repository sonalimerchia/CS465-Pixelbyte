package edu.illinois.cs465.pixelbyte.ClassList;

import android.os.FileObserver;
import androidx.annotation.Nullable;
import java.io.File;

import edu.illinois.cs465.pixelbyte.MainActivity;

public class FileListener extends FileObserver {
    MainActivity parent_;

    public FileListener(File file, MainActivity m) {
        super(file);
        parent_ = m;
    }

    @Override
    public void onEvent(int i, @Nullable String s) {
        if (i == FileObserver.MODIFY) {
            parent_.updateClass(i);
        }
    }
}
