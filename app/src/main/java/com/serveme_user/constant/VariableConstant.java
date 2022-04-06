package com.serveme_user.constant;

import com.google.firebase.auth.FirebaseAuth;

public class VariableConstant
{
    public static final int MESSAGE_RIGHT = 1;
    public static final int MESSAGE_LEFT = 0;
    public static final String User_ID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    public static final int RC_SIGN_IN = 9001;
}
