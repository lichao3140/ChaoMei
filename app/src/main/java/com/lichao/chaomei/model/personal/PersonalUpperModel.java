package com.lichao.chaomei.model.personal;

import android.support.annotation.NonNull;
import com.lichao.chaomei.contract.personal.PersonalContract;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 18:18
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class PersonalUpperModel implements PersonalContract.IPersonalUpperModel {

    @NonNull
    public static PersonalUpperModel newInstance() {
        return new PersonalUpperModel();
    }
}
