package com.cdut.sx.typeConverter;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

import javax.xml.bind.TypeConstraintException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;


public class MyDateConverter extends DefaultTypeConverter {


    public Object convertValue(Object value, Class toType) {
        // TODO Auto-generated method stub

        SimpleDateFormat sdf = null;
        try {
            if (toType == Date.class) {//从浏览器到服务器的类型转换
                String dataStr = ((String[]) value)[0];
                sdf = getSimpleDateFormat(dataStr);
                return sdf.parse(dataStr);
            } else if (toType == String.class) {
                sdf = (SimpleDateFormat) ActionContext.getContext().getSession().get("sdf");
                Date date = (Date) value;
                return sdf.format(date);
            }
        } catch (ParseException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return super.convertValue(value, toType);
    }

    private SimpleDateFormat getSimpleDateFormat(String dataStr) {

        SimpleDateFormat sdf = null;

        if (Pattern.matches("^\\d{4}/\\d{2}/\\d{2}$", dataStr)) {
            sdf = new SimpleDateFormat("yyyy/MM/dd");
        } else if (Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", dataStr)) {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        } else if (Pattern.matches("^\\d{4}.\\d{2}.\\d{2}$", dataStr)) {
            sdf = new SimpleDateFormat("yyyy.MM.dd");
        } else if (Pattern.matches("^\\d{8}$", dataStr)) {
            sdf = new SimpleDateFormat("yyyyMMdd");
        } else {
            throw new TypeConstraintException("类型转换错误");
        }
        // TODO Auto-generated method stub
        return sdf;
    }


}
