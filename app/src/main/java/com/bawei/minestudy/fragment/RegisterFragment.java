package com.bawei.minestudy.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bawei.minestudy.R;
import com.bawei.minestudy.entity.LogAndRegEntity;
import com.bawei.minestudy.mvp.contract.IContract;
import com.bawei.minestudy.mvp.model.LogAndRegModel;
import com.bawei.minestudy.mvp.presenter.RegisterPresenter;
import com.bawei.mybase.mvp.view.BaseFragment;

import org.greenrobot.eventbus.EventBus;

/**
 * MineStudy
 * name: RegisterFragment
 * time: 2021/8/19 14:35.
 * author: 王益德
 * Describe:
 */
public class RegisterFragment extends BaseFragment<RegisterPresenter> implements IContract.IRegisterView{

    private EditText regUsernameEdit;
    private EditText regPasswordEdit;
    private Button registerBtn;

    @Override
    public void initView() {
        presenter = new RegisterPresenter(this, new LogAndRegModel());
        regUsernameEdit = findViewById(R.id.reg_username_edit);
        regPasswordEdit = findViewById(R.id.reg_password_edit);
        registerBtn = findViewById(R.id.register_btn);
    }

    @Override
    public void initData() {
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.register(regUsernameEdit.getText().toString(),regPasswordEdit.getText().toString());
            }
        });
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_register;
    }

    @Override
    public void showRegister(LogAndRegEntity data) {
        if (data.getCode() == 0) {
            EventBus.getDefault().post("goLog");
        } else {
            showToast("注册失败  " + data.getMsg());
        }
    }
}
