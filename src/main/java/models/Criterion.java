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
@JsonIgnoreProperties({"laravel_through_key", "weight_percentage"})
public class Criterion {

    private String id;
    @JsonProperty("goal_id")
    private String goalId;
    private String name;
    @JsonProperty("impact_type")
    private Boolean impactType;
    @JsonProperty("impact_type_description")
    private String impactTypeDescription;
    private int index;
    private int weight;
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

    public String getGoalId() {
        return goalId;
    }

    public void setGoalId(String goalId) {
        this.goalId = goalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getImpactType() {
        return impactType;
    }

    public void setImpactType(Boolean impactType) {
        this.impactType = impactType;
    }

    public String getImpactTypeDescription() {
        return impactTypeDescription;
    }

    public void setImpactTypeDescription(String impactTypeDescription) {
        this.impactTypeDescription = impactTypeDescription;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
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
