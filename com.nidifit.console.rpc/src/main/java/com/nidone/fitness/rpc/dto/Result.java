package com.nidone.fitness.rpc.dto;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "result", namespace = "com.nidone.rpc.dto")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "result", namespace = "com.nidone.rpc.dto")
public class Result<T> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 6028636097083630372L;

    private Integer totalCount;

    private Integer totalPage;
    @XmlElement
    private T module;
    /**
     *
     */
    private List<T> items = new ArrayList();

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }



    /**
     * 是否成功
     */
    @XmlAttribute
    private Boolean success = false;

    /**
     * 返回码
     */
    @XmlAttribute
    private String resultCode;

    /**
     * 消息
     */
    @XmlAttribute
    private String message;



    /**
     * 默认的key
     */
    public static final String DEFAULT_MODEL_KEY = "value";

    /**
     * 当前的key
     */
    private String modelKey = DEFAULT_MODEL_KEY;

    private String[] resultCodeParams;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String code;
    /**
     * 默认构造方法
     */
    public Result() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 新增一个返回结果
     *
     * @param obj
     * @return
     */
    public Object addDefaultModel(T obj) {
        return module = obj;
    }

    public T getModule() {
        return module;
    }

    public void setModule(T module) {
        this.module = module;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }



    /**
     * 返回是否成功
     *
     * @return
     */

    public Boolean getSuccess() {
        return success;
    }

    public Boolean isSuccess() {
        return success;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public void setResultCode(String resultCode, String... args) {
        this.resultCode = resultCode;
        this.resultCodeParams = args;
    }

    public String[] getResultCodeParams() {
        return resultCodeParams;
    }

    public void setResultCodeParams(String[] resultCodeParams) {
        this.resultCodeParams = resultCodeParams;
    }


}
