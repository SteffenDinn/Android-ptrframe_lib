# Android-ptrframe_lib
下拉刷新Module

# 初始化
PtrFrameALayout ptrFrameLayout;

ptrFrameLayout.setPtrHandler(new PtrHandlerManager(viewContent) {

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                // 刷新后...
            }
        });
        
# 收起刷新
ptrFrameLayout.closePtr();
