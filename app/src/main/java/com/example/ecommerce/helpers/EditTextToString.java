package com.example.ecommerce.helpers;

import com.rengwuxian.materialedittext.MaterialEditText;

/**
 * Created by user on 2/12/2018.
 */

public class EditTextToString {

    public static String getString(MaterialEditText editText)
    {
        if(editText.getText()==null)
        {
            return "";
        }
        else {
            return editText.getText().toString();
        }

    }
}
