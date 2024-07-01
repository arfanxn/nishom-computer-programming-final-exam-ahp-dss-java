package models;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author arfanxn
 */
public class PerformanceScore {
    
    private String id;
    @JsonProperty("alternative_id")
    private String alternativeId;
    private Alternative alternative;
    @JsonProperty("criterion_id")
    private String criterionId;
    private Criterion criterion;
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

    public String getAlternativeId() {
        return alternativeId;
    }

    public void setAlternativeId(String alternativeId) {
        this.alternativeId = alternativeId;
    }

    public Alternative getAlternative() {
        return alternative;
    }

    public void setAlternative(Alternative alternative) {
        this.alternative = alternative;
    }

    public String getCriterionId() {
        return criterionId;
    }

    public void setCriterionId(String criterionId) {
        this.criterionId = criterionId;
    }

    public Criterion getCriterion() {
        return criterion;
    }

    public void setCriterion(Criterion criterion) {
        this.criterion = criterion;
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
