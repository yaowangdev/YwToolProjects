package com.appdev.common.view.media;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * @author 创建人 ：yaowang
 * @version 1.0
 * @package 包名 ：com.appdev.common.view.media
 * @createTime 创建时间 ：2018/11/21
 * @modifyBy 修改人 ：
 * @modifyTime 修改时间 ：
 * @modifyMemo 修改备注：
 */
public class MediaFolder implements Parcelable {
    public String name;                  //当前文件夹的名字
    public String path;                  //当前文件夹的路径
    public FileBean fileBean;            //当前文件夹需要要显示的缩略图，默认为最近的一次图片
    public ArrayList<FileBean> medias;   //当前文件夹下所有媒体(图片，视频)的集合

    public MediaFolder() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public FileBean getFileBean() {
        return fileBean;
    }

    public void setFileBean(FileBean fileBean) {
        this.fileBean = fileBean;
    }

    public ArrayList<FileBean> getMedias() {
        return medias;
    }

    public void setMedias(ArrayList<FileBean> medias) {
        this.medias = medias;
    }

    /** 只要文件夹的路径和名字相同，就认为是相同的文件夹 */
    @Override
    public boolean equals(Object o) {
        try {
            FileBean other = (FileBean) o;
            return this.path.equalsIgnoreCase(other.getPath()) && this.name.equalsIgnoreCase(other.getName());
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return super.equals(o);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.path);
        dest.writeParcelable(this.fileBean, flags);
        dest.writeTypedList(this.medias);
    }

    protected MediaFolder(Parcel in) {
        this.name = in.readString();
        this.path = in.readString();
        this.fileBean = in.readParcelable(FileBean.class.getClassLoader());
        this.medias = in.createTypedArrayList(FileBean.CREATOR);
    }

    public static final Parcelable.Creator<MediaFolder> CREATOR = new Parcelable.Creator<MediaFolder>() {
        @Override
        public MediaFolder createFromParcel(Parcel source) {
            return new MediaFolder(source);
        }

        @Override
        public MediaFolder[] newArray(int size) {
            return new MediaFolder[size];
        }
    };
}
