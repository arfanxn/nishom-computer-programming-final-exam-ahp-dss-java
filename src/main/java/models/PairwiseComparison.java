/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author arfanxn
 */
@JsonIgnoreProperties({"laravel_through_key"})
public class PairwiseComparison {
    
    private String id;
    
    @JsonProperty("primary_criterion_id")
    private String primaryCriterionId;
    @JsonProperty("primary_criterion")
    private Criterion primaryCriterion;

    @JsonProperty("secondary_criterion_id")
    private String secondaryCriterionId;
    @JsonProperty("secondary_criterion")
    private Criterion secondaryCriterion;
    
    private double value;
    
    @JsonProperty("created_at")
    private String createdAt;
    
    @JsonProperty("updated_at")
    private String updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrimaryCriterionId() {
        return primaryCriterionId;
    }

    public void setPrimaryCriterionId(String primaryCriterionId) {
        this.primaryCriterionId = primaryCriterionId;
    }

    public Criterion getPrimaryCriterion() {
        return primaryCriterion;
    }

    public void setPrimaryCriterion(Criterion primaryCriterion) {
        this.primaryCriterion = primaryCriterion;
    }

    public String getSecondaryCriterionId() {
        return secondaryCriterionId;
    }

    public void setSecondaryCriterionId(String secondaryCriterionId) {
        this.secondaryCriterionId = secondaryCriterionId;
    }

    public Criterion getSecondaryCriterion() {
        return secondaryCriterion;
    }

    public void setSecondaryCriterion(Criterion secondaryCriterion) {
        this.secondaryCriterion = secondaryCriterion;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    

}
