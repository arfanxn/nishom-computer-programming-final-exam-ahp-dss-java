/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.List;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author arfanxn
 */

public class Goal {

    private String id;
    @JsonProperty("user_id")
    private String userId;
    private String title;
    private String slug;
    private String description;
    private List<Criterion> criteria;
    private List<Alternative> alternatives;
    @JsonProperty("performance_scores")
    private List<PerformanceScore> performanceScores;
    @JsonProperty("pairwise_comparisons")
    private List<PairwiseComparison> pairwiseComparisons;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Criterion> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<Criterion> criteria) {
        this.criteria = criteria;
    }

    public List<Alternative> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<Alternative> alternatives) {
        this.alternatives = alternatives;
    }

    public List<PerformanceScore> getPerformanceScores() {
        return performanceScores;
    }

    public void setPerformanceScores(List<PerformanceScore> performanceScores) {
        this.performanceScores = performanceScores;
    }

    public List<PairwiseComparison> getPairwiseComparisons() {
        return pairwiseComparisons;
    }

    public void setPairwiseComparisons(List<PairwiseComparison> pairwiseComparisons) {
        this.pairwiseComparisons = pairwiseComparisons;
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
