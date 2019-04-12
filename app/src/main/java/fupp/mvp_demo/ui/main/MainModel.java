package fupp.mvp_demo.ui.main;

import fupp.mvp_demo.api.Api;
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
    public Observable<String> getData() {
        return Api.get().mService.getBaiDu("http://www.baidu.com/");
    }
}
