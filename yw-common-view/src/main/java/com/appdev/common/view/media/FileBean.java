package com.appdev.common.view.media;

import android.os.Parcel;
import android.os.Parcelable;

public class FileBean implements Parcelable {
    private int id;              //id
    private String name;         //文件名
    private String thumbName;    //缩略图名（图片）
    private String path;         //文件路径
    private long size;           //文件大小
    private long duration;       //播放时长（音视频）
    private String mimeType;     //文件类型
    private long createTime;     //文件创建时间
    private int icon;            //文件图标

    public FileBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbName() {
        return thumbName;
    }

    public void setThumbName(String thumbName) {
        this.thumbName = thumbName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.thumbName);
        dest.writeString(this.path);
        dest.writeLong(this.size);
        dest.writeLong(this.duration);
        dest.writeString(this.mimeType);
        dest.writeLong(this.createTime);
        dest.writeInt(this.icon);
    }

    protected FileBean(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.thumbName = in.readString();
        this.path = in.readString();
        this.size = in.readLong();
        this.duration = in.readLong();
        this.mimeType = in.readString();
        this.createTime = in.readLong();
        this.icon = in.readInt();
    }

    public static final Parcelable.Creator<FileBean> CREATOR = new Parcelable.Creator<FileBean>() {
        @Override
        public FileBean createFromParcel(Parcel source) {
            return new FileBean(source);
        }

        @Override
        public FileBean[] newArray(int size) {
            return new FileBean[size];
        }
    };
}
