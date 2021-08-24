package com.bawei.minestudy.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.minestudy.R;
import com.bawei.minestudy.activity.MainActivity;
import com.bawei.minestudy.entity.LogAndRegEntity;
import com.bawei.minestudy.mvp.contract.IContract;
import com.bawei.minestudy.mvp.model.LogAndRegModel;
import com.bawei.minestudy.mvp.presenter.LoginPresenter;
import com.bawei.minestudy.utils.SpUtils;
import com.bawei.mybase.view.BaseFragment;

import org.greenrobot.eventbus.EventBus;

/**
 * MineStudy
 * name: LoginFragment
 * time: 2021/8/19 14:35.
 * author: 王益德
 * Describe:
 */
public class LoginFragment extends BaseFragment<LoginPresenter> implements View.OnClickListener, IContract.ILoginView {

    private EditText loginUsernameEdit;
    private EditText loginPasswordEdit;
    private Button loginBtn;
    private Button goRegisterBtn;

    @Override
    public void initView() {

        presenter = new LoginPresenter(this,new LogAndRegModel());

        loginUsernameEdit = (EditText) findViewById(R.id.login_username_edit);
        loginPasswordEdit = (EditText) findViewById(R.id.login_password_edit);
        loginBtn = (Button) findViewById(R.id.login_btn);
        goRegisterBtn = (Button) findViewById(R.id.go_register_btn);
    }

    @Override
    public void initData() {
        goRegisterBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_login;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_register_btn:
                EventBus.getDefault().postSticky("goReg");
                break;
            case R.id.login_btn:
                presenter.login(loginUsernameEdit.getText().toString(),loginPasswordEdit.getText().toString());
                break;
        }
    }

    @SuppressLint("CommitPrefEdits")
    @Override
    public void showLogin(LogAndRegEntity data) {
        if (data.getCode()==0){
            Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);

            SharedPreferences login = SpUtils.getInstance().getLogin();
            login.edit().putBoolean("login",true).apply();

            getActivity().finish();
        }else {
            showToast("登录失败  "+data.getMsg());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
