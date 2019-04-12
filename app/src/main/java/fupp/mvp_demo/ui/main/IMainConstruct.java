package fupp.mvp_demo.ui.main;

import fupp.mvp_demo.base.BaseModel;
import fupp.mvp_demo.base.BasePresenter;
import fupp.mvp_demo.base.BaseView;
import okhttp3.ResponseBody;
import rx.Observable;

/**
 * <pre>
 *     author : fupp
 *     time   : 2019/04/12
 *     desc   :
 * </pre>
 */
public interface IMainConstruct {

    interface IMainModel extends BaseModel {

        Observable<ResponseBody> getData();

    }

    interface IMainView extends BaseView {

        void getDataSuccess(String result);
        void getDataFail(Throwable throwable);


    }

    abstract class IMainPresenter extends BasePresenter<IMainModel, IMainView> {


    }

}
