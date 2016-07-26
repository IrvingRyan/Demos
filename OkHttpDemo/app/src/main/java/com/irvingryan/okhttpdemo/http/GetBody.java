package com.irvingryan.okhttpdemo.http;

import java.util.ArrayList;


/**
 * 简单的get参数添加类
 * Created by wentao on 2016/7/22.
 */
public class GetBody {
    private GetBody(){
    }

    public static final class Builder {

        private ArrayList<String> mNameParam =new ArrayList<>();
        private ArrayList<String> mValueParam =new ArrayList<>();

        public Builder add(String key, int value) {
            add(key, Integer.toString(value));
            return this;
        }

        public Builder add(String key, long value) {
            add(key, Long.toString(value));
            return this;

        }

        public Builder add(String key, boolean value) {
            add(key, String.valueOf(value));
            return this;

        }

        public Builder add(String key, char value) {
            add(key, String.valueOf(value));
            return this;

        }

        public Builder add(String key, double value) {
            add(key, Double.toString(value));
            return this;

        }

        public Builder add(String key, float value) {
            add(key, Float.toString(value));
            return this;

        }

        public Builder add(String key, short value) {
            add(key, Integer.toString(value));
            return this;

        }

        public Builder add(String key, byte value) {
            add(key, Integer.toString(value));
            return this;

        }

        public Builder add(String key, String value) {
            if (value != null&&key!=null) {
                mNameParam.add(key);
                mValueParam.add(value);
            }
            return this;

        }

        @Override
        public String toString() {
            StringBuilder builder=new StringBuilder("?");
            for (int i=0,size=mNameParam.size();i<size;i++){
                if (i==0)
                    builder.append(mNameParam.get(i)+"="+mValueParam.get(i));
                else
                    builder.append("&"+mNameParam.get(i)+"="+mValueParam.get(i));
            }
            return builder.toString();
        }

        public String build() {
            return toString();
        }
    }
}
