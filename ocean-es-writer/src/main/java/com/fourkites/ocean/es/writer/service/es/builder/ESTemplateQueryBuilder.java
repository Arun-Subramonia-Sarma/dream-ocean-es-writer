package com.fourkites.ocean.es.writer.service.es.builder;

import lombok.Builder;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.script.mustache.SearchTemplateRequest;

import java.util.HashMap;
import java.util.Map;


public class ESTemplateQueryBuilder {

    private ScriptType scriptType;

    private String indexName;

    private String templateName;
    private Map<String, Object> scriptParams;

    public static ESTemplateQueryBuilder builder(){
        return new ESTemplateQueryBuilder();
    }

    public ESTemplateQueryBuilder  scriptType(ScriptType scriptType){
        this.scriptType = scriptType;
        return this;
    }

    public ESTemplateQueryBuilder indexName(String indexName){
        this.indexName=indexName;
        return this;
    }

    public ESTemplateQueryBuilder templateName(String templateName){
        this.templateName=templateName;
        return this;
    }

    public ESTemplateQueryBuilder params(Map<String, Object> scriptParams) {
        this.scriptParams=scriptParams;
        return this;
    }

    public ESTemplateQueryBuilder param(String paramName, Object paramValue){
        if(this.scriptParams==null){
           this.scriptParams=new HashMap<>();
        }
        this.scriptParams.put(paramName, paramValue);
        return this;
    }

    public SearchTemplateRequest build(){
        SearchTemplateRequest searchTemplateRequest = new SearchTemplateRequest();
        searchTemplateRequest.setRequest(new SearchRequest(indexName));
        searchTemplateRequest.setScriptType(scriptType!=null?scriptType:ScriptType.STORED);
        searchTemplateRequest.setScript(this.templateName);
        searchTemplateRequest.setScriptParams(scriptParams);
        return searchTemplateRequest;
    }
}
