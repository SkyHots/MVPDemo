package fupp.mvp.ui.main;

import fupp.mvp.api.Api;
import okhttp3.ResponseBody;
import rx.Observable;

/**
 * <pre>
 *     author : fupp
 *     time   : 2019/04/12
 *     desc   :
 * </pre>
 */
public class MainModel implements IMainConstruct.IMainModel {


    @Override
    public Observable<ResponseBody> getData() {
        return Api.get().mService.getBaiDu("http://www.baidu.com/");
    }
}
