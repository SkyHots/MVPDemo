package fupp.mvp_demo.ui.main;

import java.io.IOException;

import fupp.mvp_demo.base.RxSchedulers;

/**
 * <pre>
 *     author : fupp
 *     time   : 2019/04/12
 *     desc   :
 * </pre>
 */
public class MainPresenter extends IMainConstruct.IMainPresenter {


    @Override
    public void onStart() {
        mModel.getData().compose(RxSchedulers.io_main())
                .doOnSubscribe(() -> mView.get().showLoading())
                .subscribe(s -> {
                    mView.get().hideLoading();
                    try {
                        mView.get().getDataSuccess(s.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }, throwable -> {
                    if (mView.get() != null) {
                        mView.get().hideLoading();
                        mView.get().getDataFail(throwable);
                    }
                });
    }
}
