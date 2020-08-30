package com.agunahwanabsin.sitl.ui.user;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.agunahwanabsin.sitl.R;
import com.agunahwanabsin.sitl.api.instance.BeekeperInterface;
import com.agunahwanabsin.sitl.api.model.request.ChangePasswordRequest;
import com.agunahwanabsin.sitl.api.model.response.ChangePasswordResponse;
import com.agunahwanabsin.sitl.api.services.BeekeperApiService;
import com.agunahwanabsin.sitl.helper.SessionManager;
import com.agunahwanabsin.sitl.model.Beekeper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ChangePasswordFragment extends Fragment {

    // Global Variable
    Context mContext;
    View view;

    // View Object
    EditText passwordLama, passwordBaru, konfPasswordBaru;
    Button simpan;

    // Session Manager Class
    SessionManager session;

    // Session class
    Beekeper beekeper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_change_password, container, false);

        // Set Context
        mContext = this.getActivity().getApplicationContext();

        passwordLama = (EditText) view.findViewById(R.id.password_lama);
        passwordBaru = (EditText) view.findViewById(R.id.password_baru);
        konfPasswordBaru = (EditText) view.findViewById(R.id.conf_password_baru);
        simpan = (Button) view.findViewById(R.id.simpan);

        // Session Manager
        session = new SessionManager(mContext);

        // Get Session
        beekeper = session.getUserDetails();

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValidation()) {
                    changePassword();
                }
            }
        });

        return view;
    }

    private boolean checkValidation() {
        if (TextUtils.isEmpty(passwordLama.getText())) {
            passwordLama.setError("Password Lama is required!");
            passwordLama.setHint("please enter Password Lama");

            return false;
        }

        if (TextUtils.isEmpty(passwordBaru.getText())) {
            passwordBaru.setError("Password Baru is required!");
            passwordBaru.setHint("please enter Password Baru");

            return false;
        }

        if (!passwordBaru.getText().toString().equals(konfPasswordBaru.getText().toString())) {
            konfPasswordBaru.setError("Password Baru and Konfirmasi Password must same!");
            konfPasswordBaru.setHint("please enter Password Baru and Konfirmasi Password with same character");

            return false;
        }

        return true;
    }

    private void changePassword() {
        int idBeekeeper = beekeper.getIdBeekeper();
        String oldPassword = passwordLama.getText().toString();
        String newPassword = passwordBaru.getText().toString();
        String confirmPassword = konfPasswordBaru.getText().toString();

        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest(idBeekeeper, oldPassword, newPassword, confirmPassword);
        BeekeperInterface service = BeekeperApiService.changePassword();
        Call<ChangePasswordResponse> call = service.changePassword(changePasswordRequest);
        Callback<ChangePasswordResponse> callback = new Callback<ChangePasswordResponse>() {
            @Override
            public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
                if (response.isSuccessful()) {
                    ChangePasswordResponse changePasswordResponse = response.body();
                    Toast.makeText(mContext, changePasswordResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {
                Toast.makeText(mContext, "Error: " + t.toString(), Toast.LENGTH_LONG).show();
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        };
        call.enqueue(callback);
    }
}