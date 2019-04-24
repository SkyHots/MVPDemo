package fupp.mvp.base.util;

import android.os.Environment;

import java.io.File;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by fupp on 2017/12/1 0001.
 */

public class DeleteImageUtil {

    /**
     * 删除文件夹下的图片
     */
    public static void deleteImage() {

        File folders = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "DCIM" + File
                .separator + "EDT_ScreenShot");
        if (folders.exists())
            Observable.just(folders)
                    .flatMap(file -> Observable.from(file.listFiles()))
                    .subscribeOn(Schedulers.io())
                    .subscribe(File::delete);
        else
            folders.mkdirs();
    }
}
