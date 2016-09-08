/**
 * Canary.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package jiajun.threelevelistview.dialog;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.view.View;




/**
 * 对话框工具类
 * @author xiaomo
 * @version $Id: DialogUtils.java, v 0.1 2014年11月4日 下午4:29:13 xiaomo Exp $
 */
public class DialogUtils {




    
    
    /**
     * 显示提示对话框
     * 
     * @param mContext 上下文对象
     * @param title 标题
     * @param message 提示信息
     * @param poTitle  po按钮内容
     * @param mOkListener 
     * @param naTitle
     * @param mCancelListener
     */
    public static void showAlertDialog(Activity mContext,String title,String message,String poTitle,OnClickListener mOkListener,String naTitle,OnClickListener mCancelListener){
        android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(mContext);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setNegativeButton(poTitle, mOkListener);
        alert.setPositiveButton(naTitle, mCancelListener);
        alert.show();
    }
    /**
     * 显示提示对话框
     *
     * @param mContext 上下文对象
     * @param title 标题
     * @param message 提示信息
     * @param poTitle  po按钮内容
     * @param mOkListener
     * @param naTitle
     * @param mCancelListener
     */
    public static void showIOSAlertDialog(Activity mContext, String title, String message, String
            poTitle, View.OnClickListener mOkListener, String naTitle, View.OnClickListener
            mCancelListener){
      AlertDialog alert = new AlertDialog
                (mContext).builder();
        alert.setTitle(title);
        alert.setMsg(message);
        alert.setPositiveButton(poTitle, mOkListener);
        alert.setNegativeButton(naTitle, mCancelListener);
        alert.setCancelable(false);
        alert.show();
    }


    private static ProgressDialog mProgressDialog;

    /**
     * 显示加载对话框
     *
     * @param msg 提示内容
     */
    public static void showProgressDialog(Context mContext, String msg) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setMessage(msg);
        }
        if (!mProgressDialog.isShowing())
            mProgressDialog.show();
    }


}
