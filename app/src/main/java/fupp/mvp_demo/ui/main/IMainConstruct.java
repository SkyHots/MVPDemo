package fupp.mvp_demo.ui.main;

import fupp.mvp_demo.base.BaseModel;
import fupp.mvp_demo.base.BasePresenter;
import fupp.mvp_demo.base.BaseView;
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

        Observable<String> getData();

    }

    interface IMainView extends BaseView {


    }

    abstract class IMainPresenter extends BasePresenter<IMainView, IMainModel> {
        public abstract void getData();

    }

}
